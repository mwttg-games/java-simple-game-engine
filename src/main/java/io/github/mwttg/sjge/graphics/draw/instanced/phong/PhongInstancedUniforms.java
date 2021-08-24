package io.github.mwttg.sjge.graphics.draw.instanced.phong;

import io.github.mwttg.sjge.graphics.draw.Location;
import io.github.mwttg.sjge.graphics.draw.Uniform;
import io.github.mwttg.sjge.graphics.draw.light.PointLight;
import io.github.mwttg.sjge.graphics.draw.instanced.entity.InstancedDrawables;
import java.util.Map;

final class PhongInstancedUniforms implements Uniform {
  private final Map<String, Integer> locations;

  PhongInstancedUniforms(final int shaderProgramId) {
    this.locations = initializeLocations(shaderProgramId);
  }

  void upload(final InstancedDrawables entities, final PointLight light) {
    uploadViewMatrix(locations, entities.instancedMatrixStack().viewMatrix());
    uploadProjectionMatrix(locations, entities.instancedMatrixStack().projectionMatrix());
    uploadMaterial(locations, entities.material());
    activateTexture0(locations, entities.ids().textureId());
    uploadPointLight(locations, light);
  }

  private Map<String, Integer> initializeLocations(final int shaderProgramId) {
    return Map.ofEntries(
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
