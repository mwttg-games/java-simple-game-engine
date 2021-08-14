package com.github.mwttg.sjge.configuration;

public record OpenGlConfiguration(int openGlMajorVersion,
                                  int openGlMinorVersion,
                                  boolean vsync,
                                  boolean wireframe,
                                  boolean backfaceCulling) {

    String prettyPrint() {
        return """
                    + OpenGL Configuration
                        OpenGL version.................. %s.%s
                        vsync .......................... %s
                        wireframe ...................... %s
                        backface culling ............... %s
                """.formatted(openGlMajorVersion, openGlMinorVersion, vsync, wireframe, backfaceCulling);
    }
}
