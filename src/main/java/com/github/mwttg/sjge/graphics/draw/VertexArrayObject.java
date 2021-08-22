package com.github.mwttg.sjge.graphics.draw;

import com.github.mwttg.sjge.utilities.CleanUpUtilities;
import java.util.List;
import org.lwjgl.opengl.GL40;

/**
 * A vertex array object which holds the vertex buffer objects (ids) which are needed for rendering an object
 */
public final class VertexArrayObject {

  private VertexArrayObject() {
  }

  /**
   * creates a vertex array object
   *
   * @param vboDefinitions holds the vertex buffer object id and the size of the data type
   * @return the OpenGL id
   */
  public static int create(final List<VboDefinition> vboDefinitions) {
    final var id = GL40.glGenVertexArrays();
    CleanUpUtilities.addVertexArrayObjectId(id);
    GL40.glBindVertexArray(id);
    bindBuffers(vboDefinitions);

    return id;
  }

  private static void bindBuffers(final List<VboDefinition> vboDefinitions) {
    var index = 0;
    for (final VboDefinition definition : vboDefinitions) {
      final var vboId = definition.vertexBufferObjectId();
      final var size = definition.sizeOfDataType();
      GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, vboId);
      GL40.glVertexAttribPointer(index, size, GL40.GL_FLOAT, false, 0, 0);

      index++;
    }
  }
}
