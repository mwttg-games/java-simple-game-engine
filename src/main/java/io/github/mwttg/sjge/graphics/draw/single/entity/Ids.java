package io.github.mwttg.sjge.graphics.draw.single.entity;

import io.github.mwttg.sjge.graphics.draw.VboDefinition;
import io.github.mwttg.sjge.graphics.draw.VertexArrayObject;

public record Ids(int vaoId, int textureId, int size) {

  public static Ids create(final float[] vertices, final float[] textureCoordinates,
                    final float[] normals, final int textureId) {
    final var vboDefinitions = VboDefinition.create(vertices, textureCoordinates, normals);
    final var vaoId = VertexArrayObject.create(vboDefinitions);
    final var size = vertices.length / 3;

    return new Ids(vaoId, textureId, size);
  }
}
