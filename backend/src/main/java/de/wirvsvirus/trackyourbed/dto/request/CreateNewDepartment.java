package de.wirvsvirus.trackyourbed.dto.request;

import java.util.UUID;
import javax.validation.constraints.NotNull;

public class CreateNewDepartment {
  @NotNull
  private String name;

  @NotNull
  private UUID hospitalId;

  @NotNull
  private String departmentType;

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

  public String getDepartmentType() {
    return departmentType;
  }

  public void setDepartmentType(final String departmentType) {
    this.departmentType = departmentType;
  }
}
