package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.BedDto;
import de.wirvsvirus.trackyourbed.entity.Bed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BedDtoMapper {

  @Mapping(source = "ward.id", target = "wardId")
  @Mapping(source = "bedType.name", target = "bedType")
  @Mapping(source = "bedState.name", target = "bedState")
  BedDto entityToDto(final Bed bed);
  // TODO: add mapping method for collection

}
