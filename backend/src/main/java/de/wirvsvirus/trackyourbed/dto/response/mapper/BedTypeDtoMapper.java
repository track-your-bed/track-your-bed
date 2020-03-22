package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.BedTypeDto;
import de.wirvsvirus.trackyourbed.entity.BedType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BedTypeDtoMapper {
  BedTypeDto entityToDto(final BedType bedType);
}
