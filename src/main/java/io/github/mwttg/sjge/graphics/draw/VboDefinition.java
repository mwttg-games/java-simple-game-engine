package io.github.mwttg.sjge.graphics.draw;

import java.util.List;
import org.lwjgl.opengl.GL40;

public record VboDefinition(int vertexBufferObjectId, int sizeOfDataType) {

  public static List<VboDefinition> create(final float[] vertices, final float[] textureCoordinates,
                             final float[] normals) {
    final var vertexVboId = VertexBufferObject.create(vertices, GL40.GL_STATIC_DRAW);
    final var uvVboId = VertexBufferObject.create(textureCoordinates, GL40.GL_STATIC_DRAW);

    if (normals != null) {
      final var normalVboId = VertexBufferObject.create(normals, GL40.GL_STATIC_DRAW);
      return List.of(
          new VboDefinition(vertexVboId, 3),
          new VboDefinition(uvVboId, 2),
          new VboDefinition(normalVboId, 3));
    } else {
      return List.of(
          new VboDefinition(vertexVboId, 3),
          new VboDefinition(uvVboId, 2));
    }
  }
}
