package io.github.mwttg.sjge.graphics.draw.instanced.entity;

import io.github.mwttg.sjge.graphics.draw.VboDefinition;
import io.github.mwttg.sjge.graphics.draw.instanced.InstancedVertexArrayObject;
import io.github.mwttg.sjge.graphics.draw.instanced.InstancedVertexBufferObject;

public record InstancedIds(int modelMatrixVboId, int vaoId, int textureId, int size) {

  public static InstancedIds create(final float[] vertices, final float[] textureCoordinates,
                                    final float[] normals, final int textureId) {
    final var vboDefinitions = VboDefinition.create(vertices, textureCoordinates, normals);
    final var modelMatrixVboId = InstancedVertexBufferObject.createEmpty();
    final var vaoId = InstancedVertexArrayObject.create(vboDefinitions, modelMatrixVboId);
    final var size = vertices.length / 3;

    return new InstancedIds(modelMatrixVboId, vaoId, textureId, size);
  }
}
