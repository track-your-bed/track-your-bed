package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.WardCapacityDto;
import de.wirvsvirus.trackyourbed.entity.WardCapacity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlatCapacityDtoMapper {

  WardCapacityDto entityToDto(final WardCapacity flatCapacity);
  // TODO: add mapping method for collection

}
