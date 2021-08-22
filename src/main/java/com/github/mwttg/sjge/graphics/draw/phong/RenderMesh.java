package com.github.mwttg.sjge.graphics.draw.phong;

import com.github.mwttg.sjge.Entity;
import com.github.mwttg.sjge.graphics.draw.MatrixStack;
import java.util.Map;
import org.lwjgl.opengl.GL40;

final class RenderMesh {

  private final UploadUniforms uploadUniforms;
  private final int shaderProgramId;
  private final Map<String, Integer> uniformLocations;

  public RenderMesh(final int shaderProgramId, final Map<String, Integer> uniformLocations) {
    this.shaderProgramId = shaderProgramId;
    this.uniformLocations = uniformLocations;
    this.uploadUniforms = new UploadUniforms();
  }

  void apply(final MatrixStack matrixStack,
             final PointLight light,
             final Entity entity,
             final Material material) {
    GL40.glBindVertexArray(entity.getVertexArrayObjectId());
    GL40.glUseProgram(shaderProgramId);
    enableVertexAttribArray();

    uploadUniforms.apply(uniformLocations, matrixStack, material, light, entity.getTextureId());
    GL40.glDrawArrays(GL40.GL_TRIANGLES, 0, entity.getSize());

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
