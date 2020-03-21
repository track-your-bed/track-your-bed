package de.wirvsvirus.trackyourbed.dto.request;

import java.util.UUID;

public class UpdateStation {
  private String name;
  private UUID hospitalId;
  private String stationTypeName;

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

  public String getStationTypeName() {
    return stationTypeName;
  }

  public void setStationTypeName(final String stationTypeName) {
    this.stationTypeName = stationTypeName;
  }
}
