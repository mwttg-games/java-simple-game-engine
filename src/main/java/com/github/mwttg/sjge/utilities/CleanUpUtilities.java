package com.github.mwttg.sjge.utilities;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL40;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Functionality to clean up OpenGL
 */
public final class CleanUpUtilities {

    private static final Logger LOG = LoggerFactory.getLogger(CleanUpUtilities.class);

    private static long gameWindowId;
    private static List<Integer> shaderProgramIds = List.of();
    private static List<Integer> shaderIds = List.of();
    private static List<Integer> vertexBufferObjectIds = List.of();
    private static List<Integer> vertexArrayObjectIds = List.of();

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
     * add a vertex buffer object id for later clean up
     *
     * @param id of the vertex buffer object
     */
    public static void addVertexBufferObjectId(final int id) {
        vertexBufferObjectIds.add(id);
    }

    /**
     * add a vertex array object id for later clean up
     *
     * @param id of the vertex array object
     */
    public static void addVertexArrayObjectId(final int id) {
        vertexArrayObjectIds.add(id);
    }

    /**
     * Clean up OpenGL ids
     */
    public static void purge() {
        LOG.info("Start clean up process");
        LOG.debug("  Remove VertexArrayObjects");
        vertexArrayObjectIds.forEach(GL40::glDeleteVertexArrays);
        LOG.debug("  Remove VertexBufferObjects");
        vertexBufferObjectIds.forEach(GL40::glDeleteBuffers);
        LOG.debug("  Remove Shaders");
        shaderIds.forEach(GL40::glDeleteShader);
        LOG.debug("  Remove ShaderPrograms");
        shaderProgramIds.forEach(GL40::glDeleteProgram);
        LOG.debug("  Remove GameWindow");
        cleanUpGameWindow();
        
        vertexArrayObjectIds = List.of();
        vertexBufferObjectIds = List.of();
        shaderIds = List.of();
        shaderProgramIds = List.of();
    }

    private static void cleanUpGameWindow() {
        GLFW.glfwDestroyWindow(gameWindowId);
        GLFW.glfwTerminate();
    }
}
