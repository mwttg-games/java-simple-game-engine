package com.github.mwttg.sjge.logic;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

/**
 * The GameLoop/Main Loop
 */
public class MainLoop {

    /**
     * a simple main loop where 'nothing' happens (no rendering/ no physics/logic)
     * @param gameWindowId the window id from OpenGL
     */
    public void loop(final long gameWindowId) {
        while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GLFW.glfwSwapBuffers(gameWindowId);
            GLFW.glfwPollEvents();
        }
    }
}
