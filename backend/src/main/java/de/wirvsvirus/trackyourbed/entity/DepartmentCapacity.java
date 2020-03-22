package de.wirvsvirus.trackyourbed.entity;

import java.util.ArrayList;
import java.util.List;

public class DepartmentCapacity extends FlatCapacity {

  private List<FlatCapacity> wardCapacities;

  public DepartmentCapacity(final String name) {
    super(name);
    this.wardCapacities = new ArrayList<>();
  }

  public List<FlatCapacity> getWardCapacities() {
    return wardCapacities;
  }

  public void setWardCapacities(final List<FlatCapacity> wardCapacity) {
    this.wardCapacities = wardCapacity;
  }
}
