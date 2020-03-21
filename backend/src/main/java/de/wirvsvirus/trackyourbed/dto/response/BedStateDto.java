package de.wirvsvirus.trackyourbed.dto.response;

import java.util.UUID;
import javax.validation.Constraint;

public class BedStateDto {

  private UUID id;
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
