package com.github.mwttg.sjge.graphics.draw.phong;

import com.github.mwttg.sjge.graphics.draw.Location;
import com.github.mwttg.sjge.graphics.draw.Uniform;
import com.github.mwttg.sjge.graphics.draw.light.PointLight;
import com.github.mwttg.sjge.graphics.entity.Drawable;
import java.util.Map;

final class UploadUniforms implements Uniform {

  void apply(final Map<String, Integer> locations, final Drawable entity, final PointLight light) {
    uploadMatrixStack(locations, entity.matrixStack());
    uploadNormalMatrix(locations, entity.matrixStack().modelMatrix());
    uploadMaterial(locations, entity.material());
    activateTexture0(locations, entity.ids().textureId());
    uploadPointLight(locations, light);
  }

  public Map<String, Integer> initializeLocations(final int shaderProgramId) {
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
