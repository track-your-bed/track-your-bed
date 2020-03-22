package de.wirvsvirus.trackyourbed.dto.response;

import de.wirvsvirus.trackyourbed.entity.FlatCapacity;
import java.util.ArrayList;
import java.util.List;

public class DepartmentCapacityDto extends FlatCapacityDto {

  private List<FlatCapacity> wardCapacities;

  public DepartmentCapacityDto() {
    super();
    this.wardCapacities = new ArrayList<>();
  }

  public List<FlatCapacity> getWardCapacities() {
    return wardCapacities;
  }

  public void setWardCapacities(final List<FlatCapacity> wardCapacity) {
    this.wardCapacities = wardCapacity;
  }
}
