package de.wirvsvirus.trackyourbed.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractBaseEntity implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private UUID id;

  public UUID getId() {
    return id;
  }

  public void setId(final UUID id) {
    this.id = id;
  }

  @Override
  public boolean equals(final Object thatObject) {
    if (this == thatObject) {
      return true;
    }
    if (thatObject == null || getClass() != thatObject.getClass()) {
      return false;
    }
    final AbstractBaseEntity that = (AbstractBaseEntity) thatObject;
    return getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
