package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.WardTypeDto;
import de.wirvsvirus.trackyourbed.entity.WardType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WardTypeDtoMapper {
  WardTypeDto entityToDto(final WardType wardType);
}
