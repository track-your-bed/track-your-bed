package de.wirvsvirus.trackyourbed.dto.response;

import java.time.Instant;
import java.util.UUID;

public class BedDto {

  private UUID id;
  private String name;
  private UUID wardId;
  private String bedState;
  private String bedType;
  private Instant stateLastChanged;

  public UUID getId() {
    return id;
  }

  public BedDto setId(final UUID id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public BedDto setName(final String name) {
    this.name = name;
    return this;
  }

  public UUID getWardId() {
    return wardId;
  }

  public BedDto setWardId(final UUID wardId) {
    this.wardId = wardId;
    return this;
  }

  public String getBedState() {
    return bedState;
  }

  public BedDto  setBedState(final String bedState) {
    this.bedState = bedState;
    return this;
  }

  public String getBedType() {
    return bedType;
  }

  public BedDto setBedType(final String bedType) {
    this.bedType = bedType;
    return this;
  }

  public Instant getStateLastChanged() {
    return stateLastChanged;
  }

  public BedDto setStateLastChanged(final Instant stateLastChanged) {
    this.stateLastChanged = stateLastChanged;
    return this;
  }
}
