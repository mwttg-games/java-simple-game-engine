package io.github.mwttg.sjge.graphics.draw;

import io.github.mwttg.sjge.utilities.CleanUpUtilities;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL40;

/**
 * A vertex buffer object for vertices (geometry), texture coordinates, normals (light), etc.
 */
public final class VertexBufferObject {

  private VertexBufferObject() {
  }

  /**
   * uploads data to the graphic device
   *
   * @param data     the actual data
   * @param drawType defines how the data is rendered
   * @return the OpenGL id
   */
  public static int create(final float[] data, final int drawType) {
    final var buffer = BufferUtils.createFloatBuffer(data.length);
    buffer.put(data);
    buffer.flip();

    final var id = GL40.glGenBuffers();
    CleanUpUtilities.addVertexBufferObjectId(id);
    GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, id);
    GL40.glBufferData(GL40.GL_ARRAY_BUFFER, buffer, drawType);

    return id;
  }
}
