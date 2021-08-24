package io.github.mwttg.sjge.graphics.draw.instanced.entity;

import java.util.List;
import org.joml.Matrix4f;

public record InstancedMatrixStack(List<Matrix4f> modelMatrices, Matrix4f viewMatrix, Matrix4f projectionMatrix) {
}
