package com.github.mwttg.sjge.graphics.shader;

import com.github.mwttg.sjge.utilities.CleanUpUtilities;
import com.github.mwttg.sjge.utilities.FileUtilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A ShaderProgram is used for visualize geometry inside a {@link com.github.mwttg.sjge.graphics.window.GameWindow}
 */
public class ShaderProgram {

    private static final Logger LOG = LoggerFactory.getLogger(ShaderProgram.class);

    private ShaderProgram() {
    }

    /**
     * generates a ShaderProgram by compiling the code for a vertex- and fragment shader and uploading it to the
     * OpenGL graphics device.
     *
     * @param vertexShaderFile   the filename of the vertex shader file in the resource folder
     * @param fragmentShaderFile the filename of the fragment shader file in the resource folder
     * @return the OpenGL shader program id
     * @throws IOException if generation fails for any reason
     */
    public static int create(final String vertexShaderFile, final String fragmentShaderFile) throws IOException {
        final var shaderProgramId = GL20.glCreateProgram();
        CleanUpUtilities.addShaderProgramId(shaderProgramId);

        final var vertexShaderCode = FileUtilities.readFromResources(vertexShaderFile);
        final var fragmentShaderCode = FileUtilities.readFromResources(fragmentShaderFile);
        final var vertexShaderId = compileShader(vertexShaderCode, GL20.GL_VERTEX_SHADER);
        final var fragmentShaderId = compileShader(fragmentShaderCode, GL20.GL_FRAGMENT_SHADER);
        CleanUpUtilities.addShaderId(vertexShaderId);
        CleanUpUtilities.addShaderId(fragmentShaderId);

        GL20.glAttachShader(shaderProgramId, vertexShaderId);
        GL20.glAttachShader(shaderProgramId, fragmentShaderId);
        linkShaderProgram(shaderProgramId);
        validateShaderProgram(shaderProgramId);
        GL20.glDetachShader(shaderProgramId, vertexShaderId);
        GL20.glDetachShader(shaderProgramId, fragmentShaderId);

        return shaderProgramId;
    }

    private static void validateShaderProgram(final int shaderProgramId) throws IOException {
        GL20.glValidateProgram(shaderProgramId);
        if (GL20.glGetProgrami(shaderProgramId, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
            final var message = "Validate ShaderProgram failed: %s".formatted(GL20.glGetProgramInfoLog(shaderProgramId));
            LOG.error(message);
            throw new IOException(message);
        }
    }

    private static void linkShaderProgram(final int shaderProgramId) throws IOException {
        GL20.glLinkProgram(shaderProgramId);
        if (GL20.glGetProgrami(shaderProgramId, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            final var message = "Link ShaderProgram failed: %s".formatted(GL20.glGetProgramInfoLog(shaderProgramId));
            LOG.error(message);
            throw new IOException(message);
        }
    }

    private static int compileShader(final List<String> sourceCode, final int type) throws IOException {
        final var shaderId = GL20.glCreateShader(type);
        if (shaderId == 0) {
            throw new IOException("Can't create shader");
        }

        final var code = sourceCode.stream().filter(line -> line.equals("")).collect(Collectors.joining("\n"));
        GL20.glShaderSource(shaderId, code);
        GL20.glCompileShader(shaderId);
        if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            final var message = "Compile Shader failed: %s".formatted(GL20.glGetShaderInfoLog(shaderId));
            LOG.error(message);
            throw new IOException(message);
        }

        return shaderId;
    }
}
