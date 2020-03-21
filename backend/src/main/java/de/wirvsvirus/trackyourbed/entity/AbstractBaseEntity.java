package de.wirvsvirus.trackyourbed.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractBaseEntity {

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
}
