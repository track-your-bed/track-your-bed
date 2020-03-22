package de.wirvsvirus.trackyourbed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department extends AbstractBaseEntity {

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "hospital_id")
  private Hospital hospital;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Hospital getHospital() {
    return hospital;
  }

  public void setHospital(final Hospital hospital) {
    this.hospital = hospital;
  }

}
