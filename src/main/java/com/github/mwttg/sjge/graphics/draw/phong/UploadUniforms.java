package com.github.mwttg.sjge.graphics.draw.phong;

import com.github.mwttg.sjge.graphics.draw.Location;
import com.github.mwttg.sjge.graphics.draw.MatrixStack;
import com.github.mwttg.sjge.graphics.draw.Uniform;
import java.util.Map;

final class UploadUniforms implements Uniform {

  void apply(final Map<String, Integer> locations, final MatrixStack matrixStack,
             final Material material, final PointLight light, final int textureId) {
    uploadMatrixStack(locations, matrixStack);
    uploadNormalMatrix(locations, matrixStack.modelMatrix());
    uploadMaterial(locations, material);
    uploadPointLight(locations, light);
    activateTexture0(locations, textureId);
  }

  Map<String, Integer> initializeLocations(final int shaderProgramId) {
    return Map.ofEntries(
        createLocationFor(shaderProgramId, Location.MODEL_MATRIX),
        createLocationFor(shaderProgramId, Location.VIEW_MATRIX),
        createLocationFor(shaderProgramId, Location.PROJECTION_MATRIX),
        createLocationFor(shaderProgramId, Location.NORMAL_MATRIX),
        createLocationFor(shaderProgramId, Location.MATERIAL_AMBIENT),
        createLocationFor(shaderProgramId, Location.MATERIAL_DIFFUSE),
        createLocationFor(shaderProgramId, Location.MATERIAL_SPECULAR),
        createLocationFor(shaderProgramId, Location.MATERIAL_SPECULAR_EXPONENT),
        createLocationFor(shaderProgramId, Location.LIGHT_POSITION),
        createLocationFor(shaderProgramId, Location.LIGHT_COLOR),
        createLocationFor(shaderProgramId, Location.LIGHT_GAMMA),
        createLocationFor(shaderProgramId, Location.TEXTURE_SAMPLER));
  }
}
