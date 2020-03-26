package de.wirvsvirus.trackyourbed.dto.request;

import java.util.UUID;

public class UpdateWard {
  private String name;
  private UUID departmentId;
  private String wardType;

  public String getName() {
    return name;
  }

  public UpdateWard setName(final String name) {
    this.name = name;
    return this;
  }

  public UUID getDepartmentId() {
    return departmentId;
  }

  public UpdateWard setDepartmentId(final UUID departmentId) {
    this.departmentId = departmentId;
    return this;
  }

  public String getWardType() {
    return wardType;
  }

  public UpdateWard setWardType(final String wardTypeName) {
    this.wardType = wardTypeName;
    return this;
  }
}
