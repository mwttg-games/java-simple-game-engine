package io.github.mwttg.sjge.graphics.draw.instanced;

import io.github.mwttg.sjge.graphics.draw.instanced.entity.InstancedDrawables;
import io.github.mwttg.sjge.utilities.CleanUpUtilities;
import org.lwjgl.opengl.GL40;
import org.lwjgl.system.MemoryUtil;

public final class InstancedVertexBufferObject {

  private static final int MATRIX4F_SIZE_OF_FLOATS = 4 * 4;

  private InstancedVertexBufferObject() {
  }

  public static int createEmpty() {
    final var id = GL40.glGenBuffers();
    CleanUpUtilities.addVertexBufferObjectId(id);

    return id;
  }

  public static void pushData(final InstancedDrawables entities) {
    final var size = entities.instancedMatrixStack().modelMatrices().size();
    final var buffer = MemoryUtil.memAllocFloat(size * MATRIX4F_SIZE_OF_FLOATS);

    final var modelMatrices = entities.instancedMatrixStack().modelMatrices();
    for (int index = 0; index < size; index++) {
      final var modelMatrix = modelMatrices.get(index);
      modelMatrix.get(index * MATRIX4F_SIZE_OF_FLOATS, buffer);
    }

    GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, entities.ids().modelMatrixVboId());
    GL40.glBufferData(GL40.GL_ARRAY_BUFFER, buffer, GL40.GL_DYNAMIC_DRAW);

    MemoryUtil.memFree(buffer);
  }
}
