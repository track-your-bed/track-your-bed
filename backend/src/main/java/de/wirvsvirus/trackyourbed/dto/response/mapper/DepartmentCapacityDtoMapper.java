package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.DepartmentCapacityDto;
import de.wirvsvirus.trackyourbed.entity.DepartmentCapacity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = FlatCapacityDtoMapper.class)
public interface DepartmentCapacityDtoMapper {

  DepartmentCapacityDto entityToDto(final DepartmentCapacity departmentCapacity);
  // TODO: add mapping method for collection

}
