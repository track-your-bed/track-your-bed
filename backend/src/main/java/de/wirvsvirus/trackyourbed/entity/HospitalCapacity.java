package de.wirvsvirus.trackyourbed.entity;

import java.util.ArrayList;
import java.util.List;

public class HospitalCapacity extends FlatCapacity {

  private List<FlatCapacity> departmentCapacities;
  private List<FlatCapacity> wardCapacities;

  public HospitalCapacity(final String name) {
    super(name);
    this.wardCapacities = new ArrayList<>();
    this.departmentCapacities = new ArrayList<>();
  }

  public List<FlatCapacity> getDepartmentCapacities() {
    return departmentCapacities;
  }

  public void setDepartmentCapacities(final List<FlatCapacity> departmentCapacity) {
    this.departmentCapacities = departmentCapacity;
  }

  public List<FlatCapacity> getWardCapacities() {
    return wardCapacities;
  }

  public void setWardCapacities(final List<FlatCapacity> wardCapacity) {
    this.wardCapacities = wardCapacity;
  }
}
