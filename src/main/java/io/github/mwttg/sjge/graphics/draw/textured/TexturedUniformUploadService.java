package io.github.mwttg.sjge.graphics.draw.textured;

import io.github.mwttg.sjge.graphics.draw.Location;
import io.github.mwttg.sjge.graphics.draw.Uniform;
import java.util.Map;
import org.joml.Matrix4f;

public class TexturedUniformUploadService implements Uniform {

  public void upload(final Map<String, Integer> locations, final Matrix4f mvp,
                     final int textureId) {
    uploadModelViewPerspectiveMatrix(locations, mvp);
    activateTexture0(locations, textureId);
  }

  public Map<String, Integer> initializeLocations(int shaderProgramId) {
    return Map.ofEntries(
        createLocationFor(shaderProgramId, Location.MODEL_VIEW_PERSPECTIVE_MATRIX),
        createLocationFor(shaderProgramId, Location.TEXTURE_SAMPLER));
  }
}
