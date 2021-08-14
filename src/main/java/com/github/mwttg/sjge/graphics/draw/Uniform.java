package com.github.mwttg.sjge.graphics.draw;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL40;

import java.nio.FloatBuffer;
import java.util.Map;

public interface Uniform {

    String MODEL_VIEW_PERSPECTIVE_MATRIX = "modelViewPerspectiveMatrix";
    String TEXTURE_SAMPLER = "textureSampler";
    int CAPACITY = 16;
    FloatBuffer MATRIX_BUFFER = BufferUtils.createFloatBuffer(CAPACITY);

    default void uploadModelViewPerspectiveMatrix(final Map<String, Integer> locations, final Matrix4f modelViewPerspective) {
        final var location = locations.get(MODEL_VIEW_PERSPECTIVE_MATRIX);
        final var buffer = modelViewPerspective.get(MATRIX_BUFFER);
        GL40.glUniformMatrix4fv(location, false, buffer);
    }

    default void activateTexture0(final Map<String, Integer> locations, final int textureId) {
        final var location = locations.get(TEXTURE_SAMPLER);
        GL40.glUniform1i(location, 0);
        GL40.glActiveTexture(GL40.GL_TEXTURE0);
        GL40.glBindTexture(GL40.GL_TEXTURE_2D, textureId);
    }

    default Map.Entry<String, Integer> createLocationFor(final int shaderProgramId, final String name) {
        final var locationId = GL40.glGetUniformLocation(shaderProgramId, name);
        return Map.entry(name, locationId);
    }
}
