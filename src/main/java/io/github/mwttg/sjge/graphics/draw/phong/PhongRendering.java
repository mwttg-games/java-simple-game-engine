package io.github.mwttg.sjge.graphics.draw.phong;

import io.github.mwttg.sjge.graphics.draw.light.PointLight;
import io.github.mwttg.sjge.graphics.entity.Drawable;
import io.github.mwttg.sjge.utilities.ShaderUtilities;
import org.lwjgl.opengl.GL40;

public final class PhongRendering {

  private final int shaderProgramId;
  private final PhongUniforms uniforms;

  public PhongRendering() {
    this.shaderProgramId = ShaderUtilities.createPhongShader();
    this.uniforms = new PhongUniforms(this.shaderProgramId);
  }

  public void draw(final Drawable entity, final PointLight light) {
    GL40.glBindVertexArray(entity.ids().vaoId());
    GL40.glUseProgram(shaderProgramId);
    enableVertexAttribArray();

    uniforms.upload(entity, light);
    GL40.glDrawArrays(GL40.GL_TRIANGLES, 0, entity.ids().size());

    disableVertexAttribArray();
  }

  private void enableVertexAttribArray() {
    GL40.glEnableVertexAttribArray(0); // vertices
    GL40.glEnableVertexAttribArray(1); // texture coordinates
    GL40.glEnableVertexAttribArray(2); // normals
  }

  private void disableVertexAttribArray() {
    GL40.glDisableVertexAttribArray(2); // normals
    GL40.glDisableVertexAttribArray(1); // texture coordinates
    GL40.glDisableVertexAttribArray(0); // vertices
  }
}
