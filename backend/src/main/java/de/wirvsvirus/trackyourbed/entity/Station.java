package de.wirvsvirus.trackyourbed.entity;

import java.util.UUID;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Station {

  @Id
  @GeneratedValue
  private UUID id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hospital_id")
  private Hospital hospitalID;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "station_type_name")
  private StationType stationType;

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
}
