package de.wirvsvirus.trackyourbed.dto.response.mapper;

import de.wirvsvirus.trackyourbed.dto.response.HospitalCapacityDto;
import de.wirvsvirus.trackyourbed.entity.HospitalCapacity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = FlatCapacityDtoMapper.class)
public interface HospitalCapacityDtoMapper {

  HospitalCapacityDto entityToDto(final HospitalCapacity hospitalCapacity);
  // TODO: add mapping method for collection

}
