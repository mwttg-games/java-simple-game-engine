package io.github.mwttg.sjge.graphics.draw;

import java.util.List;
import org.lwjgl.opengl.GL40;

public abstract class Vao {

  protected static void bindBuffers(final List<VboDefinition> vboDefinitions) {
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
