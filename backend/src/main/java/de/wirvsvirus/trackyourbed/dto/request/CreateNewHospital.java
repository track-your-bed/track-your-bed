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

  public CreateNewHospital setName(final String name) {
    this.name = name;
    return this;
  }

  public int getMaxCapacity() {
    return maxCapacity;
  }

  public CreateNewHospital setMaxCapacity(final int maxCapacity) {
    this.maxCapacity = maxCapacity;
    return this;
  }

  public String getLat() {
    return lat;
  }

  public CreateNewHospital setLat(final String lat) {
    this.lat = lat;
    return this;
  }

  public String getLon() {
    return lon;
  }

  public CreateNewHospital setLon(final String lon) {
    this.lon = lon;
    return this;
  }
}
