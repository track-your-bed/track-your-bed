package de.wirvsvirus.trackyourbed.dto.response;

import de.wirvsvirus.trackyourbed.entity.WardCapacity;
import java.util.ArrayList;
import java.util.List;

public class DepartmentCapacityDto extends WardCapacityDto {

  private List<WardCapacity> wardCapacities;

  public DepartmentCapacityDto() {
    super();
    this.wardCapacities = new ArrayList<>();
  }

  public List<WardCapacity> getWardCapacities() {
    return wardCapacities;
  }

  public void setWardCapacities(final List<WardCapacity> wardCapacity) {
    this.wardCapacities = wardCapacity;
  }
}
