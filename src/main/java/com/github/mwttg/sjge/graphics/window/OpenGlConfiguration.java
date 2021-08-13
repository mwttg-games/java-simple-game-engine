package com.github.mwttg.sjge.graphics.window;

public record OpenGlConfiguration(int openGlMajorVersion,
                                  int openGlMinorVersion,
                                  boolean vsync,
                                  boolean wireframe,
                                  boolean backfaceCulling) {
}
