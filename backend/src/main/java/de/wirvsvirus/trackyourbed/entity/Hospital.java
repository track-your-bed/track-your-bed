package de.wirvsvirus.trackyourbed.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hospital")
public class Hospital {

  @Id
  @GeneratedValue
  private UUID id;

  private String name;
  private int maxCapacity;
  private String lat;

  @Column(name = "long")
  private String lon;

  public UUID getId() {
    return id;
  }

  public void setId(final UUID uuid) {
    this.id = uuid;
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

  public void setLon(final String lng) {
    this.lon = lng;
  }
}
