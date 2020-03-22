package de.wirvsvirus.trackyourbed.entity;

import java.util.ArrayList;
import java.util.List;

public class HospitalCapacity extends WardCapacity {

  private List<DepartmentCapacity> departmentCapacities;

  public HospitalCapacity(final String name) {
    super(name);
    this.departmentCapacities = new ArrayList<>();
  }

  public List<DepartmentCapacity> getDepartmentCapacities() {
    return departmentCapacities;
  }

  public void setDepartmentCapacities(final List<DepartmentCapacity> departmentCapacity) {
    this.departmentCapacities = departmentCapacity;
  }

}
