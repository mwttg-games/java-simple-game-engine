package io.github.mwttg.sjge.graphics.draw.textured;

import io.github.mwttg.sjge.graphics.entity.Drawable;
import io.github.mwttg.sjge.utilities.ShaderUtilities;
import org.lwjgl.opengl.GL40;

public final class TexturedRendering {

  private final int shaderProgramId;
  private final TexturedUniforms uniforms;

  public TexturedRendering() {
    this.shaderProgramId = ShaderUtilities.createDefaultShader();
    this.uniforms = new TexturedUniforms(this.shaderProgramId);
  }

  public void draw(final Drawable entity) {
    GL40.glBindVertexArray(entity.ids().vaoId());
    GL40.glUseProgram(shaderProgramId);
    enableVertexAttribArray();

    uniforms.upload(entity);
    GL40.glDrawArrays(GL40.GL_TRIANGLES, 0, entity.ids().size());

    disableVertexAttribArray();
  }

  private void enableVertexAttribArray() {
    GL40.glEnableVertexAttribArray(0); // vertices
    GL40.glEnableVertexAttribArray(1); // texture coordinates
  }

  private void disableVertexAttribArray() {
    GL40.glDisableVertexAttribArray(1); // texture coordinates
    GL40.glDisableVertexAttribArray(0); // vertices
  }
}
