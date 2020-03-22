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

  public void setName(final String name) {
    this.name = name;
  }

  public UUID getWardId() {
    return wardId;
  }

  public void setWardId(final UUID wardId) {
    this.wardId = wardId;
  }

  public String getBedType() {
    return bedType;
  }

  public void setBedType(final String bedType) {
    this.bedType = bedType;
  }

  public String getBedState() {
    return bedState;
  }

  public void setBedState(final String bedState) {
    this.bedState = bedState;
  }
}

