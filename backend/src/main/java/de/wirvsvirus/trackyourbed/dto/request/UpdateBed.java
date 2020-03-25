package de.wirvsvirus.trackyourbed.dto.request;

import java.util.UUID;

public class UpdateBed {

  private String name;
  private UUID wardId;
  private String bedType;
  private String bedState;

  public String getName() {
    return name;
  }

  public UpdateBed setName(final String name) {
    this.name = name;
    return this;
  }

  public UUID getWardId() {
    return wardId;
  }

  public UpdateBed setWardId(final UUID wardId) {
    this.wardId = wardId;
    return this;
  }

  public String getBedType() {
    return bedType;
  }

  public UpdateBed setBedType(final String bedType) {
    this.bedType = bedType;
    return this;
  }

  public String getBedState() {
    return bedState;
  }

  public UpdateBed setBedState(final String bedState) {
    this.bedState = bedState;
    return this;
  }
}

