package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.DepartmentTypeDto;
import de.wirvsvirus.trackyourbed.entity.DepartmentType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentTypeDtoMapper {
  DepartmentTypeDto entityToDto(final DepartmentType departmentType);
}
