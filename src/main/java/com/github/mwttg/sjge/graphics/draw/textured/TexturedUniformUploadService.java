package com.github.mwttg.sjge.graphics.draw.textured;

import com.github.mwttg.sjge.graphics.draw.Uniform;
import java.util.Map;
import org.joml.Matrix4f;

public class TexturedUniformUploadService implements Uniform {

  public void upload(final Map<String, Integer> locations, final Matrix4f mvp,
                     final int textureId) {
    uploadModelViewPerspectiveMatrix(locations, mvp);
    activateTexture0(locations, textureId);
  }

  public Map<String, Integer> initializeLocations(int shaderProgramId) {
    final var mvp = createLocationFor(shaderProgramId, MODEL_VIEW_PERSPECTIVE_MATRIX);
    final var sampler = createLocationFor(shaderProgramId, TEXTURE_SAMPLER);
    return Map.ofEntries(mvp, sampler);
  }
}
