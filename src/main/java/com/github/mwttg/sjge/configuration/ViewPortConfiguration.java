package com.github.mwttg.sjge.configuration;

public record ViewPortConfiguration(float fieldOfView, float nearPlane, float farPlane) {

  String prettyPrint() {
    return """
            + View Port Configuration
                Field of View .................. %s
                Near Plane ..................... %s
                Far Plane ...................... %s
        """.formatted(fieldOfView, nearPlane, farPlane);
  }
}
