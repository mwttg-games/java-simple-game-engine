package io.github.mwttg.sjge.graphics.draw.single.phong;

import io.github.mwttg.sjge.graphics.draw.Location;
import io.github.mwttg.sjge.graphics.draw.Uniform;
import io.github.mwttg.sjge.graphics.draw.light.PointLight;
import io.github.mwttg.sjge.graphics.draw.single.entity.Drawable;
import java.util.Map;

final class PhongUniforms implements Uniform {

  private final Map<String, Integer> locations;

  PhongUniforms(final int shaderProgramId) {
    this.locations = initializeLocations(shaderProgramId);
  }

  void upload(final Drawable entity, final PointLight light) {
    uploadMatrixStack(locations, entity.matrixStack());
    uploadMaterial(locations, entity.material());
    activateTexture0(locations, entity.ids().textureId());
    uploadPointLight(locations, light);
  }

  private Map<String, Integer> initializeLocations(final int shaderProgramId) {
    return Map.ofEntries(
        createLocationFor(shaderProgramId, Location.MODEL_MATRIX),
        createLocationFor(shaderProgramId, Location.VIEW_MATRIX),
        createLocationFor(shaderProgramId, Location.PROJECTION_MATRIX),
        createLocationFor(shaderProgramId, Location.MATERIAL_AMBIENT),
        createLocationFor(shaderProgramId, Location.MATERIAL_DIFFUSE),
        createLocationFor(shaderProgramId, Location.MATERIAL_SPECULAR),
        createLocationFor(shaderProgramId, Location.MATERIAL_SPECULAR_EXPONENT),
        createLocationFor(shaderProgramId, Location.LIGHT_POSITION),
        createLocationFor(shaderProgramId, Location.LIGHT_COLOR),
        createLocationFor(shaderProgramId, Location.LIGHT_GAMMA),
        createLocationFor(shaderProgramId, Location.TEXTURE_SAMPLER));
  }
}
