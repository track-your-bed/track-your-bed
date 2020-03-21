package de.wirvsvirus.trackyourbed.dto.response;

import java.util.UUID;
import javax.persistence.ForeignKey;

public class StationDto {

  private UUID id;
  private String name;

  private UUID hospitalID;
  private UUID stationTypeID;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getHospitalID() {
    return hospitalID;
  }

  public void setHospitalID(UUID hospitalID) {
    this.hospitalID = hospitalID;
  }

  public UUID getStationTypeID() {
    return stationTypeID;
  }

  public void setStationTypeID(UUID stationTypeID) {
    this.stationTypeID = stationTypeID;
  }
}
