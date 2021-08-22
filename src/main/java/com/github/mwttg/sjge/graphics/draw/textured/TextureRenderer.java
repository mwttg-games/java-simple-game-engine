package com.github.mwttg.sjge.graphics.draw.textured;

import com.github.mwttg.sjge.Entity;
import java.util.Map;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL40;

public class TextureRenderer {

  private final TexturedUniformUploadService uploadService;
  private final int shaderProgramId;
  private final Map<String, Integer> uniforms;

  public TextureRenderer(final int shaderProgramId, final Map<String, Integer> uniforms) {
    this.shaderProgramId = shaderProgramId;
    this.uniforms = uniforms;
    this.uploadService = new TexturedUniformUploadService();
  }

  public void draw(final Matrix4f projectionView, final Entity entity) {
    final var mvp = entity.getModelViewPerspective(projectionView);
    GL40.glBindVertexArray(entity.getVertexArrayObjectId());
    GL40.glUseProgram(shaderProgramId);
    enableVertexAttribArray();

    uploadService.upload(uniforms, mvp, entity.getTextureId());
    GL40.glDrawArrays(GL40.GL_TRIANGLES, 0, entity.getSize());

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


