package de.wirvsvirus.trackyourbed.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UpdateHospital {

  private String name;

  @Range(min = 0, message = "Please enter positive numbers only for attribute \"maxCapacity\".")
  private Integer maxCapacity;

  private String lat;

  @JsonProperty("long")
  private String lon;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Integer getMaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCapacity(final Integer maxCapacity) {
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
