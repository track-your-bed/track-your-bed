package de.wirvsvirus.trackyourbed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ward_type")
public class WardType extends AbstractBaseEntity<WardType> {

  @Column(name = "name")
  private String name;

  public String getName() {
    return name;
  }

  public WardType setName(final String name) {
    this.name = name;
    return this;
  }

}
