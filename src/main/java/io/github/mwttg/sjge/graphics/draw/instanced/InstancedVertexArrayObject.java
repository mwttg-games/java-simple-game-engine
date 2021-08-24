package io.github.mwttg.sjge.graphics.draw.instanced;

import io.github.mwttg.sjge.graphics.draw.Vao;
import io.github.mwttg.sjge.graphics.draw.VboDefinition;
import io.github.mwttg.sjge.utilities.CleanUpUtilities;
import java.util.List;
import org.lwjgl.opengl.GL40;

public final class InstancedVertexArrayObject extends Vao {

  private static final int VECTOR4F_SIZE_IN_BYTE = 4 * 4;
  private static final int MATRIX4f_SIZE_IN_BYTE = 4 * VECTOR4F_SIZE_IN_BYTE;

  private InstancedVertexArrayObject() {
  }

  public static int create(final List<VboDefinition> vboDefinitions, final int instanceVboId) {
    final var id = GL40.glGenVertexArrays();
    CleanUpUtilities.addVertexArrayObjectId(id);
    GL40.glBindVertexArray(id);
    bindBuffers(vboDefinitions);
    bindInstance(instanceVboId);

    return id;
  }

  // activate vertex attrib array 4 - 7 for Matrix4f (4 * Vector4f)
  private static void bindInstance(final int instanceVboId) {
    GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, instanceVboId);

    var index = 0;
    for (int i = 3; i < 7; i++) {
      GL40.glVertexAttribPointer(i, 4, GL40.GL_FLOAT, false, MATRIX4f_SIZE_IN_BYTE, (long) index * VECTOR4F_SIZE_IN_BYTE);
      GL40.glVertexAttribDivisor(i, 1);
      GL40.glEnableVertexAttribArray(i);
      index++;
    }
  }
}
