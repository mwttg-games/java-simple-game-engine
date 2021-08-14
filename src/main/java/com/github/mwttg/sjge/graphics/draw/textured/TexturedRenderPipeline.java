package com.github.mwttg.sjge.graphics.draw.textured;

import com.github.mwttg.sjge.Entity;
import com.github.mwttg.sjge.utilities.ShaderUtilities;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class TexturedRenderPipeline {

    private final TextureRenderer renderer;
    private final List<Entity> entities;

    public TexturedRenderPipeline() {
        this.entities = new ArrayList<>();
        final var shaderProgramId = ShaderUtilities.createDefaultShader();
        final var uniformService = new TexturedUniformUploadService();
        final var uniforms = uniformService.initializeLocations(shaderProgramId);

        this.renderer = new TextureRenderer(shaderProgramId, uniforms);
    }

    public void addEntity(final Entity entity) {
        entities.add(entity);
    }

    public boolean removeEntity(final Entity entity) {
        return entities.remove(entity);
    }

    public void draw(final Matrix4f projectionView) {
        entities.forEach(e -> renderer.draw(projectionView, e));
    }
}
