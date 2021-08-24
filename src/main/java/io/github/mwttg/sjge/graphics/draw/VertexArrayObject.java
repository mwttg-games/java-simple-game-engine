package io.github.mwttg.sjge.graphics.draw;

import io.github.mwttg.sjge.utilities.CleanUpUtilities;
import java.util.List;
import org.lwjgl.opengl.GL40;

/**
 * A vertex array object which holds the vertex buffer objects (ids) which are needed for rendering an object
 */
public final class VertexArrayObject extends Vao {

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
}
