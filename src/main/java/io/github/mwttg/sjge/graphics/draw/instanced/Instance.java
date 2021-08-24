package io.github.mwttg.sjge.graphics.draw.instanced;

public record Instance(int instanceVertexBufferObjectId, int pointerStart, int size, int stride,
                       int pointerSize) {
}
