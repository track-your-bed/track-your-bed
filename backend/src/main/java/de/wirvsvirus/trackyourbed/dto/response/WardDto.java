package de.wirvsvirus.trackyourbed.dto.response;

import java.util.Collection;
import java.util.UUID;

public class WardDto {

  private UUID id;
  private String name;
  private UUID departmentId;
  private String wardType;
  private Collection<BedDto> beds;

  public UUID getId() {
    return id;
  }

  public WardDto setId(final UUID id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public WardDto setName(final String name) {
    this.name = name;
    return this;
  }

  public UUID getDepartmentId() {
    return departmentId;
  }

  public WardDto setDepartmentId(final UUID departmentId) {
    this.departmentId = departmentId;
    return this;
  }

  public String getWardType() {
    return wardType;
  }

  public WardDto setWardType(final String wardType) {
    this.wardType = wardType;
    return this;
  }

  public Collection<BedDto> getBeds() {
    return beds;
  }

  public WardDto setBeds(final Collection<BedDto> beds) {
    this.beds = beds;
    return this;
  }
}
