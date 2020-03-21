package de.wirvsvirus.trackyourbed.dto.request.mapper;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewHospital;
import de.wirvsvirus.trackyourbed.entity.Hospital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateNewHospitalMapper {
  Hospital dtoToEntity(final CreateNewHospital createNewHospital);
}
