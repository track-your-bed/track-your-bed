package de.wirvsvirus.trackyourbed.entity;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bed")
public class Bed extends AbstractBaseEntity {

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "ward_id")
  private Ward ward;

  @ManyToOne
  @JoinColumn(name = "bed_type_name", referencedColumnName = "name")
  private BedType bedType;

  @ManyToOne
  @JoinColumn(name = "bed_state_name", referencedColumnName = "name")
  private BedState bedState;

  @Column(name = "state_last_changed")
  @GeneratedValue
  private Instant stateLastChanged;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Ward getWard() {
    return ward;
  }

  public void setWard(final Ward ward) {
    this.ward = ward;
  }

  public BedType getBedType() {
    return bedType;
  }

  public void setBedType(final BedType bedType) {
    this.bedType = bedType;
  }

  public BedState getBedState() {
    return bedState;
  }

  public void setBedState(final BedState bedState) {
    this.bedState = bedState;
  }

  public Instant getStateLastChanged() {
    return stateLastChanged;
  }

  public void setStateLastChanged(final Instant occupiedLastUpdated) {
    this.stateLastChanged = occupiedLastUpdated;
  }
}
