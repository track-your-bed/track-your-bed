package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.DepartmentDto;
import de.wirvsvirus.trackyourbed.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = WardDtoMapper.class)
public interface DepartmentDtoMapper {

  @Mapping(source = "hospital.id", target = "hospitalId")
  DepartmentDto entityToDto(final Department department);
  // TODO: add mapping method for collection

}
