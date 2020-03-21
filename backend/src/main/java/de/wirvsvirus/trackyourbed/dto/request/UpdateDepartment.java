package de.wirvsvirus.trackyourbed.dto.request;

import java.util.UUID;

public class UpdateDepartment {
  private String name;
  private UUID hospitalId;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public UUID getHospitalId() {
    return hospitalId;
  }

  public void setHospitalId(final UUID hospitalId) {
    this.hospitalId = hospitalId;
  }
}
