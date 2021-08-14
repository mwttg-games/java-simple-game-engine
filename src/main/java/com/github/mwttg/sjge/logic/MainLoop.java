package com.github.mwttg.sjge.logic;

import com.github.mwttg.sjge.Entity;
import com.github.mwttg.sjge.configuration.Configuration;
import com.github.mwttg.sjge.graphics.draw.textured.TexturedRenderPipeline;
import com.github.mwttg.sjge.utilities.MatrixStackUtilities;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

/**
 * The GameLoop/Main Loop
 */
public final class MainLoop {

    private final TexturedRenderPipeline pipeline;
    private final Matrix4f staticViewProjection;

    public MainLoop(final Configuration configuration) {
        this.pipeline = new TexturedRenderPipeline();
        final var projectionMatrix = MatrixStackUtilities.getProjectionMatrix(configuration);
        final var viewMatrix = MatrixStackUtilities.getDefaultViewMatrix();
        this.staticViewProjection = new Matrix4f(projectionMatrix).mul(new Matrix4f(viewMatrix));
    }

    /**
     * a simple main loop
     *
     * @param gameWindowId the window id from OpenGL
     */
    public void loop(final long gameWindowId) {
        while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
            GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);

            pipeline.draw(staticViewProjection);

            GLFW.glfwSwapBuffers(gameWindowId);
            GLFW.glfwPollEvents();
        }
    }

    public void addEntity(final Entity entity) {
        pipeline.addEntity(entity);
    }

    public boolean removeEntity(final Entity entity) {
        return pipeline.removeEntity(entity);
    }
}
