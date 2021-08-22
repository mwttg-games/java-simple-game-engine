package com.github.mwttg.sjge.graphics.draw.phong;

import org.joml.Vector3f;

public record Material(Vector3f ambientColor, Vector3f diffuseColor, Vector3f specularColor,
                       float specularExponent) {
}
