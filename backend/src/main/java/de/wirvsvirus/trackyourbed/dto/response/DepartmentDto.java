package de.wirvsvirus.trackyourbed.dto.response;

import java.util.Collection;
import java.util.UUID;

public class DepartmentDto {

  private UUID id;
  private String name;
  private UUID hospitalId;
  private Collection<WardDto> wards;

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

  public Collection<WardDto> getWards() {
    return wards;
  }

  public void setWards(final Collection<WardDto> wards) {
    this.wards = wards;
  }
}
