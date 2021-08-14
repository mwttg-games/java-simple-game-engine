package com.github.mwttg.sjge.utilities;

import com.github.mwttg.sjge.configuration.Configuration;
import org.joml.Matrix4f;

public final class MatrixStackUtilities {

    private MatrixStackUtilities() {
    }

    public static Matrix4f getProjectionMatrix(final Configuration configuration) {
        final var fov = configuration.viewPortConfiguration().fieldOfView();
        final var near = configuration.viewPortConfiguration().nearPlane();
        final var far = configuration.viewPortConfiguration().farPlane();
        final var width = configuration.gameWindowConfiguration().width();
        final var height = configuration.gameWindowConfiguration().height();
        final var aspectRatio = (float) width / height;
        final var result = new Matrix4f();

        return result.setPerspective((float) Math.toRadians(fov), aspectRatio, near, far);
    }

    public static Matrix4f getDefaultViewMatrix() {
        return new Matrix4f().setLookAt(
                0.0f,
                0.0f,
                10.0f,
                0.0f,
                0.0f,
                0.0f,
                0.0f,
                1.0f,
                0.0f);
    }
}
