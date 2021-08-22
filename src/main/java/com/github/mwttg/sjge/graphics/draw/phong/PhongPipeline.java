package com.github.mwttg.sjge.graphics.draw.phong;

import com.github.mwttg.sjge.Entity;
import com.github.mwttg.sjge.graphics.draw.MatrixStack;
import com.github.mwttg.sjge.utilities.ShaderUtilities;
import java.util.ArrayList;
import java.util.List;

public class PhongPipeline {

  private final RenderMesh renderMesh;
  private final List<Entity> entities;


  public PhongPipeline() {
    entities = new ArrayList<>();
    final var shaderProgramId = ShaderUtilities.createPhongShader();
    final var uploadUniforms = new UploadUniforms();
    final var uniformLocations = uploadUniforms.initializeLocations(shaderProgramId);

    this.renderMesh = new RenderMesh(shaderProgramId, uniformLocations);
  }

  public void addEntity(final Entity entity) {
    entities.add(entity);
  }

  public boolean removeEntity(final Entity entity) {
    return entities.remove(entity);
  }

  public void draw(final MatrixStack matrixStack, final PointLight light, final Material material) {
    entities.forEach(e -> renderMesh.apply(matrixStack, light, e, material));
  }
}
