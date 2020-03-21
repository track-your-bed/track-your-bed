package de.wirvsvirus.trackyourbed.dto.response;

import java.util.UUID;

public class StationDto {

  private UUID id;

  private String name;

  private UUID hospitalId;
  private UUID stationTypeId;

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

  public UUID getHospitalId() {
    return hospitalId;
  }

  public void setHospitalId(final UUID hospitalId) {
    this.hospitalId = hospitalId;
  }

  public UUID getStationTypeId() {
    return stationTypeId;
  }

  public void setStationTypeId(final UUID stationTypeId) {
    this.stationTypeId = stationTypeId;
  }
}
