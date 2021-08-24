package io.github.mwttg.sjge.graphics.draw.instanced.entity;

import io.github.mwttg.sjge.graphics.draw.material.Material;

public record InstancedDrawables(InstancedIds ids, InstancedMatrixStack instancedMatrixStack,
                                 Material material) {
}
