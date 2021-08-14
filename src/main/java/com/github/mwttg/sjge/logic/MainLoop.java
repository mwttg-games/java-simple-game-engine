package com.github.mwttg.sjge.logic;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;

/**
 * The GameLoop/Main Loop
 */
public final class MainLoop {

    /**
     * a simple main loop where 'nothing' happens (no rendering/ no physics/logic)
     *
     * @param gameWindowId the window id from OpenGL
     */
    public void loop(final long gameWindowId) {
        while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
            GL40.glClear(GL40.GL_COLOR_BUFFER_BIT | GL40.GL_DEPTH_BUFFER_BIT);
            GLFW.glfwSwapBuffers(gameWindowId);
            GLFW.glfwPollEvents();
        }
    }
}
