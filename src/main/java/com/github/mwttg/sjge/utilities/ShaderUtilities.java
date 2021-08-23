package com.github.mwttg.sjge.utilities;

import com.github.mwttg.sjge.graphics.draw.ShaderProgram;

public final class ShaderUtilities {

  private ShaderUtilities() {
  }

  public static int createDefaultShader() {
    return ShaderProgram.create("shaders/textured/vertex-shader.glsl",
        "shaders/textured/fragment-shader.glsl");
  }

  public static int createPhongShader() {
    return ShaderProgram.create("shaders/phong/vertex-shader.glsl",
        "shaders/phong/fragment-shader.glsl");
  }
}
