package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.CapacityDto;
import de.wirvsvirus.trackyourbed.entity.Capacity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CapacityDtoMapper {

  CapacityDto entityToDto(final Capacity capacity);

}
