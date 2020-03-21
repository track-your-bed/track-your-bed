package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.StationDto;
import de.wirvsvirus.trackyourbed.entity.Station;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StationDtoMapper {

  @Mapping(source = "hospital.id", target = "hospitalId")
  @Mapping(source = "stationType.id", target = "stationTypeId")
  StationDto entityToDto(final Station station);
}
