package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.FlatCapacityDto;
import de.wirvsvirus.trackyourbed.entity.FlatCapacity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlatCapacityDtoMapper {

  FlatCapacityDto entityToDto(final FlatCapacity flatCapacity);

}
