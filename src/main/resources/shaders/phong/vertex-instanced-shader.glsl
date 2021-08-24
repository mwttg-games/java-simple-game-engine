#version 410

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textureCoordinate;
layout (location = 2) in vec3 vertexNormal;
layout (location = 3) in mat4 modelMatrix;

struct PointLight {
    vec3 color;
    vec3 position;
    float gamma;
};

uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;
uniform PointLight pointLight;

out vec3 fragPosition;
out vec2 fragTextureCoordinate;
out vec3 fragNormal;
out vec3 fragLightPosition;

void main() {
    fragTextureCoordinate = textureCoordinate;
    fragNormal = mat3(transpose(inverse(viewMatrix * modelMatrix))) * vertexNormal;
    fragPosition = vec3(viewMatrix * modelMatrix * vec4(position, 1.0));
    fragLightPosition = vec3(viewMatrix * vec4(pointLight.position, 1.0));

    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position, 1.0);
}