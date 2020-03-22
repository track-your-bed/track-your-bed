package de.wirvsvirus.trackyourbed.dto.request.mapper;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewWard;
import de.wirvsvirus.trackyourbed.entity.Ward;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateNewWardMapper {
  Ward dtoToEntity(final CreateNewWard createNewWard);
}
