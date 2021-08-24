package io.github.mwttg.sjge.graphics.draw.instanced.phong;

import io.github.mwttg.sjge.graphics.draw.instanced.InstancedVertexBufferObject;
import io.github.mwttg.sjge.graphics.draw.light.PointLight;
import io.github.mwttg.sjge.graphics.draw.instanced.entity.InstancedDrawables;
import io.github.mwttg.sjge.utilities.ShaderUtilities;
import org.lwjgl.opengl.GL40;

public final class PhongInstancedRendering {

  private final int shaderProgramId;
  private final PhongInstancedUniforms uniforms;

  public PhongInstancedRendering() {
    this.shaderProgramId = ShaderUtilities.createInstancedPhongShader();
    this.uniforms = new PhongInstancedUniforms(shaderProgramId);
  }

  public void draw(final InstancedDrawables entities, final PointLight light) {
    GL40.glBindVertexArray(entities.ids().vaoId());
    GL40.glUseProgram(shaderProgramId);
    enableVertexAttribArray();

    uniforms.upload(entities, light);
    InstancedVertexBufferObject.pushData(entities);
    GL40.glDrawArraysInstanced(GL40.GL_TRIANGLES, 0, entities.ids().size(), entities.instancedMatrixStack().modelMatrices().size());

    disableVertexAttribArray();
  }

  private void enableVertexAttribArray() {
    GL40.glEnableVertexAttribArray(0); // vertices
    GL40.glEnableVertexAttribArray(1); // texture coordinates
    GL40.glEnableVertexAttribArray(2); // normals
    GL40.glEnableVertexAttribArray(3); // Matrix4f (1)
    GL40.glEnableVertexAttribArray(4); // Matrix4f (2)
    GL40.glEnableVertexAttribArray(5); // Matrix4f (3)
    GL40.glEnableVertexAttribArray(6); // Matrix4f (4)
  }

  private void disableVertexAttribArray() {
    GL40.glDisableVertexAttribArray(6); // Matrix4f (4)
    GL40.glDisableVertexAttribArray(5); // Matrix4f (3)
    GL40.glDisableVertexAttribArray(4); // Matrix4f (2)
    GL40.glDisableVertexAttribArray(3); // Matrix4f (1)
    GL40.glDisableVertexAttribArray(2); // normals
    GL40.glDisableVertexAttribArray(1); // texture coordinates
    GL40.glDisableVertexAttribArray(0); // vertices
  }
}
