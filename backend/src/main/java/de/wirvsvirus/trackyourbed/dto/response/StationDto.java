package de.wirvsvirus.trackyourbed.dto.response;

import java.util.UUID;

public class StationDto {

  private UUID id;

  private String name;

  private UUID departmentId;
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

  public UUID getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(final UUID departmentId) {
    this.departmentId = departmentId;
  }

  public UUID getStationTypeId() {
    return stationTypeId;
  }

  public void setStationTypeId(final UUID stationTypeId) {
    this.stationTypeId = stationTypeId;
  }

}
