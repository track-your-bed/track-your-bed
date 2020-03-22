package de.wirvsvirus.trackyourbed.dto.request.mapper;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewWard;
import de.wirvsvirus.trackyourbed.entity.Ward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateNewWardMapper {

  @Mapping(target = "wardType", ignore = true)
  Ward dtoToEntity(final CreateNewWard createNewWard);
}
