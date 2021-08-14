package com.github.mwttg.sjge.utilities;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CleanUpUtilities {

    private static final Logger LOG = LoggerFactory.getLogger(CleanUpUtilities.class);

    private static long gameWindowId;

    private CleanUpUtilities() {
    }

    public static void setGameWindowId(final Long id) {
        gameWindowId = id;
    }

    public static void purge() {
        LOG.info("Start clean up process");
        LOG.debug("  Remove GameWindow");
        cleanUpGameWindow();
    }

    private static void cleanUpGameWindow() {
        GLFW.glfwDestroyWindow(gameWindowId);
        GLFW.glfwTerminate();
    }
}
