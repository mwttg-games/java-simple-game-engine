#version 410

in vec3 fragPosition;
in vec2 fragTextureCoordinate;
in vec3 fragNormal;
in vec3 fragLightPosition;

struct PointLight {
    vec3 color;
    vec3 position;
    float gamma;
};

struct Material {
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    float specularExponent;
};

uniform sampler2D textureSampler;
uniform Material meshMaterial;
uniform PointLight pointLight;

out vec4 finalColor;


vec4 phongShading() {
    vec3 normal = normalize(fragNormal);
    vec3 directionSurfaceToLight = normalize(fragLightPosition - fragPosition);
    vec3 directionSurfaceToCamera = normalize(- fragPosition);
    vec3 reflectionDirection = reflect(-directionSurfaceToLight, normal);
    vec4 textureColor = texture(textureSampler, fragTextureCoordinate);

    // ambient
    vec3 ambient = (meshMaterial.ambient).rgb;

    // diffuse
    float diffuseFactor = max(dot(directionSurfaceToLight, normal), 0.0);
    vec3 diffuse = diffuseFactor * meshMaterial.diffuse;

    // specular
    vec3 specular = vec3(0.0);
    if (diffuseFactor > 0) {
        float specularFactor = max(dot(reflectionDirection, directionSurfaceToCamera), 0.0);
        specularFactor = pow(specularFactor, meshMaterial.specularExponent);
        specular = specularFactor * meshMaterial.specular;
    }

    vec3 color = (ambient + diffuse + specular) * textureColor.rgb * pointLight.color;
    vec3 gamma = vec3(1.0 / pointLight.gamma);
    return vec4(pow(color,gamma), textureColor.a);
}

void main() {
    finalColor = phongShading();
}