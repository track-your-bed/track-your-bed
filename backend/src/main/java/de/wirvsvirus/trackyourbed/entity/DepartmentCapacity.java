package de.wirvsvirus.trackyourbed.entity;

import java.util.ArrayList;
import java.util.List;

public class DepartmentCapacity extends WardCapacity {

  private List<WardCapacity> wardCapacities;

  public DepartmentCapacity(final String name) {
    super(name);
    this.wardCapacities = new ArrayList<>();
  }

  public List<WardCapacity> getWardCapacities() {
    return wardCapacities;
  }

  public void setWardCapacities(final List<WardCapacity> wardCapacity) {
    this.wardCapacities = wardCapacity;
  }
}
