package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.BedStateDto;
import de.wirvsvirus.trackyourbed.entity.BedState;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BedStateDtoMapper {
  BedStateDto entityToDto(final BedState bedState);

}
