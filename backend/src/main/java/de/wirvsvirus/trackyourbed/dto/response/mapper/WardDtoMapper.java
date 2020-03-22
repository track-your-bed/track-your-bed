package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.WardDto;
import de.wirvsvirus.trackyourbed.entity.Ward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WardDtoMapper {

  @Mapping(source = "department.id", target = "departmentId")
  @Mapping(source = "wardType.id", target = "wardTypeId")
  WardDto entityToDto(final Ward ward);
}
