package com.github.mwttg.sjge.utilities;

import com.github.mwttg.sjge.graphics.draw.ShaderProgram;

import java.io.IOException;

public final class ShaderUtilities {

    private ShaderUtilities() {
    }

    public static int createDefaultShader() {
        try {
            return ShaderProgram.create("shaders/textured/vertex-shader.glsl", "shaders/textured/fragment-shader.glsl");
        } catch (final IOException e) {
            // TODO ;)
            throw new RuntimeException(e);
        }
    }
}
