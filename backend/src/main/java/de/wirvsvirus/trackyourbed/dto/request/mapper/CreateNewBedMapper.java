package de.wirvsvirus.trackyourbed.dto.request.mapper;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewBed;
import de.wirvsvirus.trackyourbed.entity.Bed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateNewBedMapper {

  @Mapping(target = "bedType", ignore = true)
  @Mapping(target = "bedState", ignore = true)
  Bed dtoToEntity(final CreateNewBed createNewBed);

}
