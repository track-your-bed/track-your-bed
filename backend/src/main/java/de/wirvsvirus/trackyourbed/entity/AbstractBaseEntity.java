package de.wirvsvirus.trackyourbed.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractBaseEntity<T extends AbstractBaseEntity<T>> implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private UUID id;

  public UUID getId() {
    return id;
  }

  @SuppressWarnings("unchecked")
  public T setId(final UUID id) {
    this.id = id;
    return (T) this;
  }

  @Override
  public final boolean equals(final Object thatObject) {
    if (this == thatObject) {
      return true;
    }
    if (thatObject == null || getClass() != thatObject.getClass()) {
      return false;
    }
    final AbstractBaseEntity<?> that = (AbstractBaseEntity<?>) thatObject;
    return getId().equals(that.getId());
  }

  @Override
  public final int hashCode() {
    return Objects.hash(getId());
  }

}
