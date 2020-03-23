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

  public CreateNewBed setName(final String name) {
    this.name = name;
    return this;
  }

  public UUID getWardId() {
    return wardId;
  }

  public CreateNewBed setWardId(final UUID wardId) {
    this.wardId = wardId;
    return this;
  }

  public String getBedType() {
    return bedType;
  }

  public CreateNewBed setBedType(final String bedType) {
    this.bedType = bedType;
    return this;
  }

  public String getBedState() {
    return bedState;
  }

  public CreateNewBed setBedState(final String bedState) {
    this.bedState = bedState;
    return this;
  }
}
