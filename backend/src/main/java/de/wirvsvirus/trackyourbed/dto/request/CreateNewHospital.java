package de.wirvsvirus.trackyourbed.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class CreateNewHospital {

  @NotNull(message = "attribute \"name\" must be set.")
  private String name;

  @Range(min = 0, message = "Please enter positive numbers only for attribute \"maxCapacity\".")
  private int maxCapacity;

  private String lat;

  @JsonProperty("long")
  private String lon;

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
