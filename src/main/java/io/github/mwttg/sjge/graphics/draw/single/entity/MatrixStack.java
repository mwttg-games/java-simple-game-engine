package io.github.mwttg.sjge.graphics.draw.single.entity;

import org.joml.Matrix4f;

public record MatrixStack(Matrix4f modelMatrix, Matrix4f viewMatrix, Matrix4f projectionMatrix) {
}
