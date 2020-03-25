package de.wirvsvirus.trackyourbed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bed_state")
public class BedState extends AbstractBaseEntity<BedState> {

  @Column(name = "name")
  private String name;

  public String getName() {
    return name;
  }

  public BedState setName(final String name) {
    this.name = name;
    return this;
  }
}
