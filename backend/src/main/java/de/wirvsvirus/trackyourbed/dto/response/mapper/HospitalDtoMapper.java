package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.HospitalDto;
import de.wirvsvirus.trackyourbed.entity.Hospital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DepartmentDtoMapper.class)
public interface HospitalDtoMapper {

  HospitalDto entityToDto(final Hospital hospital);
  // TODO: add mapping method for collection

}
