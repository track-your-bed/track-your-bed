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

  public void setId(final UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public UUID getWardId() {
    return wardId;
  }

  public void setWardId(final UUID wardId) {
    this.wardId = wardId;
  }

  public String getBedState() {
    return bedState;
  }

  public void setBedState(final String bedState) {
    this.bedState = bedState;
  }

  public String getBedType() {
    return bedType;
  }

  public void setBedType(final String bedType) {
    this.bedType = bedType;
  }

  public Instant getStateLastChanged() {
    return stateLastChanged;
  }

  public void setStateLastChanged(final Instant stateLastChanged) {
    this.stateLastChanged = stateLastChanged;
  }
}
