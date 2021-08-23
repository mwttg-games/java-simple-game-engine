package io.github.mwttg.sjge.graphics.draw.textured;

import io.github.mwttg.sjge.graphics.draw.Location;
import io.github.mwttg.sjge.graphics.draw.Uniform;
import io.github.mwttg.sjge.graphics.entity.Drawable;
import java.util.Map;

final class TexturedUniforms implements Uniform {

  private final Map<String, Integer> locations;

  TexturedUniforms(final int shaderProgramId) {
    this.locations = initializeLocations(shaderProgramId);
  }

  void upload(final Drawable entity) {
    uploadMatrixStack(locations, entity.matrixStack());
    activateTexture0(locations, entity.ids().textureId());
  }

  private Map<String, Integer> initializeLocations(int shaderProgramId) {
    return Map.ofEntries(
        createLocationFor(shaderProgramId, Location.MODEL_MATRIX),
        createLocationFor(shaderProgramId, Location.VIEW_MATRIX),
        createLocationFor(shaderProgramId, Location.PROJECTION_MATRIX),
        createLocationFor(shaderProgramId, Location.TEXTURE_SAMPLER));
  }
}
