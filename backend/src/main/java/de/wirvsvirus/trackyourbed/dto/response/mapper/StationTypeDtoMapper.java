package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.StationTypeDto;
import de.wirvsvirus.trackyourbed.entity.StationType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StationTypeDtoMapper {
  StationTypeDto entityToDto(final StationType stationType);
}
