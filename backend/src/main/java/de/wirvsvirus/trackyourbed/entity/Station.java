package de.wirvsvirus.trackyourbed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "station")
public class Station extends AbstractBaseEntity {

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "hospital_id")
  private Hospital hospital;

  @ManyToOne
  @JoinColumn(name = "station_type_name", referencedColumnName = "name")
  private StationType stationType;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Hospital getHospital() {
    return hospital;
  }

  public void setHospital(final Hospital hospitalID) {
    this.hospital = hospitalID;
  }

  public StationType getStationType() {
    return stationType;
  }

  public void setStationType(final StationType stationType) {
    this.stationType = stationType;
  }
}
