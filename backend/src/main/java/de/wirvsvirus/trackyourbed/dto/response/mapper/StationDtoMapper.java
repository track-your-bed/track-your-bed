package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.StationDto;
import de.wirvsvirus.trackyourbed.entity.Station;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StationDtoMapper {
  StationDto entityToDto(final Station station);
}
