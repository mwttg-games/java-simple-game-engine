package io.github.mwttg.sjge.graphics.draw;

import io.github.mwttg.sjge.graphics.draw.light.PointLight;
import io.github.mwttg.sjge.graphics.draw.material.Material;
import io.github.mwttg.sjge.graphics.draw.single.entity.MatrixStack;
import java.nio.FloatBuffer;
import java.util.Map;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL40;

public interface Uniform {

  int CAPACITY = 16;
  FloatBuffer MATRIX_BUFFER = BufferUtils.createFloatBuffer(CAPACITY);

  default void activateTexture0(final Map<String, Integer> locations, final int textureId) {
    final var location = locations.get(Location.TEXTURE_SAMPLER);
    GL40.glUniform1i(location, 0);
    GL40.glActiveTexture(GL40.GL_TEXTURE0);
    GL40.glBindTexture(GL40.GL_TEXTURE_2D, textureId);
  }

  default void uploadPointLight(final Map<String, Integer> locations, final PointLight light) {
    uploadVector3f(locations.get(Location.LIGHT_POSITION), light.position());
    uploadVector3f(locations.get(Location.LIGHT_COLOR), light.color());
    GL40.glUniform1f(locations.get(Location.LIGHT_GAMMA), light.gamma());
  }

  default void uploadMaterial(final Map<String, Integer> locations, final Material material) {
    uploadVector3f(locations.get(Location.MATERIAL_AMBIENT), material.ambientColor());
    uploadVector3f(locations.get(Location.MATERIAL_DIFFUSE), material.diffuseColor());
    uploadVector3f(locations.get(Location.MATERIAL_SPECULAR), material.specularColor());
    GL40.glUniform1f(locations.get(Location.MATERIAL_SPECULAR_EXPONENT),
        material.specularExponent());
  }

  default void uploadMatrixStack(final Map<String, Integer> locations,
                                 final MatrixStack matrixStack) {
    uploadModelMatrix(locations, matrixStack.modelMatrix());
    uploadViewMatrix(locations, matrixStack.viewMatrix());
    uploadProjectionMatrix(locations, matrixStack.projectionMatrix());
  }

  private void uploadModelMatrix(final Map<String, Integer> locations, final Matrix4f modelMatrix) {
    final var location = locations.get(Location.MODEL_MATRIX);
    final var buffer = modelMatrix.get(MATRIX_BUFFER);
    GL40.glUniformMatrix4fv(location, false, buffer);
  }

  default void uploadViewMatrix(final Map<String, Integer> locations, final Matrix4f viewMatrix) {
    final var location = locations.get(Location.VIEW_MATRIX);
    final var buffer = viewMatrix.get(MATRIX_BUFFER);
    GL40.glUniformMatrix4fv(location, false, buffer);
  }

  default void uploadProjectionMatrix(final Map<String, Integer> locations,
                                      final Matrix4f projectionMatrix) {
    final var location = locations.get(Location.PROJECTION_MATRIX);
    final var buffer = projectionMatrix.get(MATRIX_BUFFER);
    GL40.glUniformMatrix4fv(location, false, buffer);
  }

  private void uploadVector3f(final int locationId, final Vector3f vector) {
    GL40.glUniform3f(locationId, vector.x, vector.y, vector.z);
  }

  default Map.Entry<String, Integer> createLocationFor(final int shaderProgramId,
                                                       final String name) {
    final var locationId = GL40.glGetUniformLocation(shaderProgramId, name);
    return Map.entry(name, locationId);
  }
}
