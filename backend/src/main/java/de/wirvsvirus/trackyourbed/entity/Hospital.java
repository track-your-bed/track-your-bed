package de.wirvsvirus.trackyourbed.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hospital")
public class Hospital extends AbstractBaseEntity<Hospital> {

  @Column(name = "name")
  private String name;

  @Column(name = "max_capacity")
  private int maxCapacity;

  @Column(name = "lat")
  private String lat;

  @Column(name = "long")
  private String lon;

  @OneToMany(mappedBy = "hospital")
  private List<Department> departments;

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

  public List<Department> getDepartments() {
    return departments;
  }

  public void setDepartments(final List<Department> departments) {
    this.departments = departments;
  }
}
