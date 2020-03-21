package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewStation;
import de.wirvsvirus.trackyourbed.dto.request.mapper.CreateNewStationRequestMapper;
import de.wirvsvirus.trackyourbed.dto.response.StationDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.StationDtoMapper;
import de.wirvsvirus.trackyourbed.entity.Department;
import de.wirvsvirus.trackyourbed.entity.Station;
import de.wirvsvirus.trackyourbed.entity.StationType;
import de.wirvsvirus.trackyourbed.excpetion.NoSuchDepartmentExcpetion;
import de.wirvsvirus.trackyourbed.excpetion.NoSuchHospitalException;
import de.wirvsvirus.trackyourbed.excpetion.NoSuchStationException;
import de.wirvsvirus.trackyourbed.excpetion.NoSuchStationTypeException;
import de.wirvsvirus.trackyourbed.persistence.DepartmentRepository;
import de.wirvsvirus.trackyourbed.persistence.StationRepository;
import de.wirvsvirus.trackyourbed.persistence.StationTypeRepository;
import de.wirvsvirus.trackyourbed.dto.request.UpdateStation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StationService {

  private final StationRepository stationRepository;
  private final DepartmentRepository departmentRepository;
  private final StationTypeRepository stationTypeRepository;
  private final CreateNewStationRequestMapper createNewStationRequestMapper;
  private final StationDtoMapper stationDtoMapper;

  @Inject
  public StationService(
      final StationRepository stationRepository,
      final DepartmentRepository departmentRepository,
      final StationTypeRepository stationTypeRepository,
      final CreateNewStationRequestMapper createNewStationRequestMapper,
      final StationDtoMapper stationDtoMapper) {
    this.stationRepository = stationRepository;
    this.departmentRepository = departmentRepository;
    this.stationTypeRepository = stationTypeRepository;
    this.createNewStationRequestMapper = createNewStationRequestMapper;
    this.stationDtoMapper = stationDtoMapper;
  }

  public StationDto createNewStation(final CreateNewStation createNewStation) {
    final Station toSave = createNewStationRequestMapper.dtoToEntity(createNewStation);
    final Department department = departmentRepository.findById(createNewStation.getDepartmentId())
        .orElseThrow(NoSuchDepartmentExcpetion::new);
    // formatter:off
    final StationType stationType = stationTypeRepository
        .findByName(createNewStation.getStationTypeName())
        .orElseThrow(() -> new NoSuchStationTypeException(createNewStation.getStationTypeName()));
    // formatter:on
    toSave.setDepartment(department);
    toSave.setStationType(stationType);
    final Station saved = stationRepository.save(toSave);
    return stationDtoMapper.entityToDto(saved);
  }

  public Collection<StationDto> getAllStations() {
    final ArrayList<StationDto> result = new ArrayList<>();
    stationRepository.findAll()
        .forEach(station -> result.add(stationDtoMapper.entityToDto(station)));
    return result;
  }

  public StationDto getStationById(final UUID id) {
    final Station fetched = stationRepository.findById(id)
        .orElseThrow(() -> new NoSuchStationException(id));
    return stationDtoMapper.entityToDto(fetched);
  }

  @Transactional
  public StationDto updateStation(final UUID id, final UpdateStation updateStation) {
    final Station station = stationRepository.findById(id).orElseThrow(() -> new NoSuchStationException(id));
    if (updateStation.getName() != null) {
      station.setName(updateStation.getName());
    }
    if (updateStation.getDepartmentId() != null) {
      final Department department = departmentRepository.findById(updateStation.getDepartmentId())
          .orElseThrow(NoSuchDepartmentExcpetion::new);
      station.setDepartment(department);
    }
    if (updateStation.getStationTypeName() != null) {
      final StationType stationType =
          stationTypeRepository.findByName(updateStation.getStationTypeName())
              .orElseThrow(() -> new NoSuchStationTypeException(updateStation.getStationTypeName()));
      station.setStationType(stationType);
    }
    return stationDtoMapper.entityToDto(station);
  }

}
