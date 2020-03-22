package de.wirvsvirus.trackyourbed.dto.request;

import java.util.UUID;

public class UpdateWard {
  private String name;
  private UUID departmentId;
  private String wardTypeName;

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

  public String getWardType() {
    return wardTypeName;
  }

  public void setWardType(final String wardTypeName) {
    this.wardTypeName = wardTypeName;
  }
}
