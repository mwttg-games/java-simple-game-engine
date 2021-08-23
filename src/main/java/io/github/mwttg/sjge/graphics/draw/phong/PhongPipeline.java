package io.github.mwttg.sjge.graphics.draw.phong;

import io.github.mwttg.sjge.graphics.draw.light.PointLight;
import io.github.mwttg.sjge.graphics.entity.Drawable;
import io.github.mwttg.sjge.utilities.ShaderUtilities;

public class PhongPipeline {

  private final Draw draw;

  public PhongPipeline() {
    final var shaderProgramId = ShaderUtilities.createPhongShader();
    final var uploadUniforms = new UploadUniforms();
    final var uniformLocations = uploadUniforms.initializeLocations(shaderProgramId);

    this.draw = new Draw(shaderProgramId, uniformLocations);
  }

  public void draw(final Drawable entity, final PointLight light) {
    draw.apply(entity, light);
  }
}
