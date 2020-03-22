package de.wirvsvirus.trackyourbed.dto.request;

import java.util.UUID;
import javax.validation.constraints.NotNull;

public class CreateNewBed {

  @NotNull
  private String name;

  @NotNull
  private UUID wardId;

  @NotNull
  private String bedType;

  @NotNull
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
