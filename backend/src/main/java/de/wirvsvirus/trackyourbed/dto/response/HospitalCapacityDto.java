package de.wirvsvirus.trackyourbed.dto.response;

import de.wirvsvirus.trackyourbed.entity.WardCapacity;
import java.util.ArrayList;
import java.util.List;

public class HospitalCapacityDto extends WardCapacityDto {

  private List<WardCapacity> departmentCapacities;

  public HospitalCapacityDto() {
    super();
    this.departmentCapacities = new ArrayList<>();
  }

  public List<WardCapacity> getDepartmentCapacities() {
    return departmentCapacities;
  }

  public void setDepartmentCapacities(final List<WardCapacity> departmentCapacity) {
    this.departmentCapacities = departmentCapacity;
  }

}
