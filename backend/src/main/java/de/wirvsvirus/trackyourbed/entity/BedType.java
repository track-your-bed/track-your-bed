package de.wirvsvirus.trackyourbed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bed_type")
public class BedType extends AbstractBaseEntity {

  @Column(name = "name")
  private String name;

  public String getName() {
    return name;
  }

  public BedType setName(final String name) {
    this.name = name;
    return this;
  }

}
