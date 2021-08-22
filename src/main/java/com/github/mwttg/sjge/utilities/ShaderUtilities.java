package com.github.mwttg.sjge.utilities;

import com.github.mwttg.sjge.graphics.draw.ShaderProgram;
import java.io.IOException;

public final class ShaderUtilities {

  private ShaderUtilities() {
  }

  public static int createDefaultShader() {
    try {
      return ShaderProgram.create("shaders/textured/vertex-shader.glsl",
          "shaders/textured/fragment-shader.glsl");
    } catch (final IOException e) {
      // TODO ;)
      throw new RuntimeException(e);
    }
  }

  public static int createPhongShader() {
    try {
      return ShaderProgram.create("shaders/phong/vertex-shader.glsl",
          "shaders/phong/fragment-shader.glsl");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
