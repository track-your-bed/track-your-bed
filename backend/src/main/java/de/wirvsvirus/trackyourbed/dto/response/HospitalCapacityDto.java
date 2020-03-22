package de.wirvsvirus.trackyourbed.dto.response;

import de.wirvsvirus.trackyourbed.entity.FlatCapacity;
import java.util.ArrayList;
import java.util.List;

public class HospitalCapacityDto extends FlatCapacityDto {

  private List<FlatCapacity> departmentCapacities;
  private List<FlatCapacity> wardCapacities;

  public HospitalCapacityDto() {
    super();
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
