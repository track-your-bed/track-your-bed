package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.response.StationTypeDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.StationTypeDtoMapper;
import de.wirvsvirus.trackyourbed.entity.StationType;
import de.wirvsvirus.trackyourbed.persistence.StationTypeRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class StationTypeService {

  private final StationTypeRepository stationTypeRepository;
  private final StationTypeDtoMapper stationTypeDtoMapper;

  @Inject
  public StationTypeService(
      final StationTypeRepository stationTypeRepository,
      final StationTypeDtoMapper stationTypeDtoMapper) {
    this.stationTypeRepository = stationTypeRepository;
    this.stationTypeDtoMapper = stationTypeDtoMapper;
  }

  public StationTypeDto getStationTypeByName(final String name) {
    final StationType fetched = stationTypeRepository.findByName(name)
        .orElseThrow(NoSuchElementException::new);
    return stationTypeDtoMapper.entityToDto(fetched);
  }

  public Collection<StationTypeDto> getAllStationTypes() {
    final Collection<StationTypeDto> allStationTypes = new ArrayList<>();
    stationTypeRepository.findAll().forEach(entity -> allStationTypes.add(stationTypeDtoMapper.entityToDto(entity)));
    return allStationTypes;
  }

}
