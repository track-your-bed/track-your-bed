package de.wirvsvirus.trackyourbed.dto.request.mapper;


import de.wirvsvirus.trackyourbed.dto.request.CreateNewHospital;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewStation;
import de.wirvsvirus.trackyourbed.entity.Station;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateNewStationRequestMapper {

  Station dtoToEntity(final CreateNewStation createNewStation);


}
