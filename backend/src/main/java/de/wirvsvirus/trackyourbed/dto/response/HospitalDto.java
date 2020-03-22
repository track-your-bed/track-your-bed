package de.wirvsvirus.trackyourbed.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class HospitalDto {

  private UUID id;
  private String name;
  private int maxCapacity;
  private String lat;

  @JsonProperty("long")
  private String lon;

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

  public int getMaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCapacity(final int maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(final String lat) {
    this.lat = lat;
  }

  public String getLon() {
    return lon;
  }

  public void setLon(final String lon) {
    this.lon = lon;
  }

}
