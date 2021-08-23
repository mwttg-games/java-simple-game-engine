package io.github.mwttg.sjge;

import java.util.Objects;
import java.util.UUID;
import org.joml.Matrix4f;

public class Entity {

  private final UUID uuid;
  private final int vertexArrayObjectId; // TODO extract
  private final int textureId;
  private final int size;
  private float positionX; // TODO extract
  private float positionY;
  private float positionZ;

  public Entity(final int vertexArrayObjectId, final int textureId, final int size) {
    this.uuid = UUID.randomUUID();
    this.vertexArrayObjectId = vertexArrayObjectId;
    this.textureId = textureId;
    this.size = size;
  }

  public void moveTo(final float x, final float y, final float z) {
    this.positionX = x;
    this.positionY = y;
    this.positionZ = z;
  }

  public Matrix4f getModelViewPerspective(final Matrix4f projectionView) {
    final var modelMatrix = new Matrix4f().translate(positionX, positionY, positionZ);
    return new Matrix4f(projectionView).mul(modelMatrix);
  }

  public UUID getUuid() {
    return uuid;
  }

  public int getVertexArrayObjectId() {
    return vertexArrayObjectId;
  }

  public int getTextureId() {
    return textureId;
  }

  public int getSize() {
    return size;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    Entity entity = (Entity) o;
    return vertexArrayObjectId == entity.vertexArrayObjectId && textureId == entity.textureId &&
        size == entity.size && Float.compare(entity.positionX, positionX) == 0 &&
        Float.compare(entity.positionY, positionY) == 0 &&
        Float.compare(entity.positionZ, positionZ) == 0 && uuid.equals(entity.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, vertexArrayObjectId, textureId, size, positionX, positionY,
        positionZ);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Entity{");
    sb.append("uuid=").append(uuid);
    sb.append(", vertexArrayObjectId=").append(vertexArrayObjectId);
    sb.append(", textureId=").append(textureId);
    sb.append(", size=").append(size);
    sb.append(", positionX=").append(positionX);
    sb.append(", positionY=").append(positionY);
    sb.append(", positionZ=").append(positionZ);
    sb.append('}');
    return sb.toString();
  }
}
