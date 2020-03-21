package de.wirvsvirus.trackyourbed.dto.request;

import java.util.UUID;

public class UpdateStation {
  private String name;
  private UUID departmentId;
  private String stationTypeName;

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

  public String getStationTypeName() {
    return stationTypeName;
  }

  public void setStationTypeName(final String stationTypeName) {
    this.stationTypeName = stationTypeName;
  }
}
