package de.wirvsvirus.trackyourbed.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bed_state")
public class BedState extends AbstractBaseEntity {

  @Column(name = "name")
  private String name;

  public String getName() {
    return name;
  }

}
