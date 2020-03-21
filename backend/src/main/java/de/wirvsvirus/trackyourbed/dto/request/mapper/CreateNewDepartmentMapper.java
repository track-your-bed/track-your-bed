package de.wirvsvirus.trackyourbed.dto.request.mapper;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewDepartment;
import de.wirvsvirus.trackyourbed.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateNewDepartmentMapper {
  Department dtoToEntity(final CreateNewDepartment createNewDepartment);
}
