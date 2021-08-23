package io.github.mwttg.sjge.graphics.draw.textured;

import io.github.mwttg.sjge.graphics.draw.Location;
import io.github.mwttg.sjge.graphics.draw.Uniform;
import io.github.mwttg.sjge.graphics.entity.Drawable;
import java.util.Map;

public class UploadUniforms implements Uniform {

  public void apply(final Map<String, Integer> locations, final Drawable entity) {
    uploadMatrixStack(locations, entity.matrixStack());
    activateTexture0(locations, entity.ids().textureId());
  }

  public Map<String, Integer> initializeLocations(int shaderProgramId) {
    return Map.ofEntries(
        createLocationFor(shaderProgramId, Location.MODEL_MATRIX),
        createLocationFor(shaderProgramId, Location.VIEW_MATRIX),
        createLocationFor(shaderProgramId, Location.PROJECTION_MATRIX),
        createLocationFor(shaderProgramId, Location.TEXTURE_SAMPLER));
  }
}
