package io.github.mwttg.sjge.graphics.draw.textured;

import io.github.mwttg.sjge.graphics.entity.Drawable;
import io.github.mwttg.sjge.utilities.ShaderUtilities;

public class TexturedPipeline {

  private final Draw draw;

  public TexturedPipeline() {
    final var shaderProgramId = ShaderUtilities.createDefaultShader();
    final var uploadUniforms = new UploadUniforms();
    final var uniformLocations = uploadUniforms.initializeLocations(shaderProgramId);

    this.draw = new Draw(shaderProgramId, uniformLocations);
  }

  public void draw(final Drawable entity) {
    draw.apply(entity);
  }
}
