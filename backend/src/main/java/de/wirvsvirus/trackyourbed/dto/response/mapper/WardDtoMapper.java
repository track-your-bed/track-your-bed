package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.WardDto;
import de.wirvsvirus.trackyourbed.entity.Ward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BedDtoMapper.class)
public interface WardDtoMapper {

  @Mapping(source = "department.id", target = "departmentId")
  @Mapping(source = "wardType.name", target = "wardType")
  WardDto entityToDto(final Ward ward);
}
