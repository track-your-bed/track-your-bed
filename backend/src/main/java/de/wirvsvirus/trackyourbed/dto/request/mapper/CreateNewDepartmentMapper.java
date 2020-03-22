package de.wirvsvirus.trackyourbed.dto.request.mapper;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewDepartment;
import de.wirvsvirus.trackyourbed.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateNewDepartmentMapper {

  @Mapping(target = "departmentType", ignore = true)
  Department dtoToEntity(final CreateNewDepartment createNewDepartment);

}
