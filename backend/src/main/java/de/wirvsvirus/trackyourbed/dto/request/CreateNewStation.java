package de.wirvsvirus.trackyourbed.dto.request;

import java.util.UUID;
import javax.validation.constraints.NotNull;

public class CreateNewStation {

  @NotNull(message = "attribute \"id\" must be set.")
  private UUID id;

  @NotNull(message = "attribute \"name\" must be set.")
  private String name;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
