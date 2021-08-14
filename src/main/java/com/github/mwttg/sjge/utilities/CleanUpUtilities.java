package com.github.mwttg.sjge.utilities;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL20;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Functionality to clean up OpenGL
 */
public class CleanUpUtilities {

    private static final Logger LOG = LoggerFactory.getLogger(CleanUpUtilities.class);

    private static long gameWindowId;
    private static List<Integer> shaderProgramIds = List.of();
    private static List<Integer> shaderIds = List.of();

    private CleanUpUtilities() {
    }

    /**
     * add the GameWindow id for later cleanup
     *
     * @param id of the GameWindow
     */
    public static void setGameWindowId(final Long id) {
        gameWindowId = id;
    }

    /**
     * add a ShaderProgram id for later clean up
     *
     * @param id of the ShaderProgram
     */
    public static void addShaderProgramId(final int id) {
        shaderProgramIds.add(id);
    }

    /**
     * add a Shader id for later clean up
     *
     * @param id of the Shader
     */
    public static void addShaderId(final int id) {
        shaderIds.add(id);
    }

    /**
     * Clean up OpenGL ids
     */
    public static void purge() {
        LOG.info("Start clean up process");
        LOG.debug("  Remove Shaders");
        shaderIds.forEach(GL20::glDeleteShader);
        LOG.debug("  Remove ShaderPrograms");
        shaderProgramIds.forEach(GL20::glDeleteProgram);
        LOG.debug("  Remove GameWindow");
        cleanUpGameWindow();
    }

    private static void cleanUpGameWindow() {
        GLFW.glfwDestroyWindow(gameWindowId);
        GLFW.glfwTerminate();
    }
}
