package io.github.mwttg.sjge.graphics.draw.single.entity;

import io.github.mwttg.sjge.graphics.draw.material.Material;

/**
 * Bundles everything that is needed to render an object
 */
public record Drawable(Ids ids, MatrixStack matrixStack, Material material) {
}
