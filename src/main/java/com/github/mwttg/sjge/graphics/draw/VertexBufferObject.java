package com.github.mwttg.sjge.graphics.draw;

import com.github.mwttg.sjge.utilities.CleanUpUtilities;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

public final class VertexBufferObject {

    private VertexBufferObject() {
    }

    public static int create(final float[] data, final int drawType) {
        final var buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();

        final var id = GL15.glGenBuffers();
        CleanUpUtilities.addVertexBufferObjectId(id);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, drawType);

        return id;
    }
}
