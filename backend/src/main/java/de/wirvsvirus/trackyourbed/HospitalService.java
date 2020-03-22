package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewHospital;
import de.wirvsvirus.trackyourbed.dto.request.UpdateHospital;
import de.wirvsvirus.trackyourbed.dto.request.mapper.CreateNewHospitalMapper;
import de.wirvsvirus.trackyourbed.dto.response.HospitalCapacityDto;
import de.wirvsvirus.trackyourbed.dto.response.HospitalDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.HospitalCapacityDtoMapper;
import de.wirvsvirus.trackyourbed.dto.response.mapper.HospitalDtoMapper;
import de.wirvsvirus.trackyourbed.entity.Hospital;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchHospitalException;
import de.wirvsvirus.trackyourbed.persistence.HospitalRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * The type Hospital service.
 */
@Service
public class HospitalService {

  private final HospitalRepository hospitalRepository;
  private final CreateNewHospitalMapper createNewHospitalMapper;
  private final HospitalDtoMapper hospitalDtoMapper;
  private final HospitalCapacityDtoMapper hospitalCapacityDtoMapper;
  private final CapacityService capacityService;

  /**
   * Instantiates a new Hospital service.
   *
   * @param hospitalRepository
   *     the hospital repository.
   * @param createNewHospitalMapper
   *     the create new request mapper.
   * @param hospitalDtoMapper
   *     the hospital dto mapper.
   * @param hospitalCapacityDtoMapper
   *     the capacity dto mapper
   */
  @Inject
  public HospitalService(final HospitalRepository hospitalRepository,
      final CreateNewHospitalMapper createNewHospitalMapper,
      final HospitalDtoMapper hospitalDtoMapper,
      final CapacityService capacityService,
      final HospitalCapacityDtoMapper hospitalCapacityDtoMapper) {
    this.hospitalRepository = hospitalRepository;
    this.createNewHospitalMapper = createNewHospitalMapper;
    this.hospitalDtoMapper = hospitalDtoMapper;
    this.capacityService = capacityService;
    this.hospitalCapacityDtoMapper = hospitalCapacityDtoMapper;
  }

  /**
   * Creates and persists a new {@code Hospital} from a {@link CreateNewHospital} request.
   *
   * @param createNewHospital
   *     the new hospital to create.
   *
   * @return a {@link HospitalDto}, representing the newly created hospital.
   */
  @Transactional
  public HospitalDto createHospital(final CreateNewHospital createNewHospital) {
    final Hospital toSave = createNewHospitalMapper.dtoToEntity(createNewHospital);
    final Hospital saved = hospitalRepository.save(toSave);
    return hospitalDtoMapper.entityToDto(saved);
  }

  public void deleteHospital(final UUID hospitalId) {
    hospitalRepository.deleteById(hospitalId);
  }

  /**
   * Retrieves a {@link Hospital} by id from the persistence layer.
   *
   * @param hospitalId
   *     the id of the hospital to retrieve.
   *
   * @return a {@link HospitalDto}, representing the newly created hospital.
   */
  public HospitalDto getHospitalById(final UUID hospitalId) {
    final Hospital retrieved =
        hospitalRepository.findById(hospitalId).orElseThrow(() -> new NoSuchHospitalException(hospitalId));
    return hospitalDtoMapper.entityToDto(retrieved);
  }

  @Transactional
  public Collection<HospitalDto> getAllHospitals() {
    final ArrayList<HospitalDto> allHospitals = new ArrayList<>();
    hospitalRepository.findAll()
        .forEach(entity -> allHospitals.add(hospitalDtoMapper.entityToDto(entity)));
    return allHospitals;
  }

  @Transactional
  public HospitalDto updateHospital(final UUID hospitalId, final UpdateHospital updateHospital) {
    final Hospital toBeUpdated =
        hospitalRepository.findById(hospitalId).orElseThrow(() -> new NoSuchHospitalException(hospitalId));

    if (updateHospital.getName() != null) {
      toBeUpdated.setName(updateHospital.getName());
    }
    if (updateHospital.getMaxCapacity() != null) {
      toBeUpdated.setMaxCapacity(updateHospital.getMaxCapacity());
    }
    if (updateHospital.getLon() != null) {
      toBeUpdated.setLon(updateHospital.getLon());
    }
    if (updateHospital.getLat() != null) {
      toBeUpdated.setLat(updateHospital.getLat());
    }

    return hospitalDtoMapper.entityToDto(toBeUpdated);
  }

  public void deleteHospitalById(final UUID id) {
    hospitalRepository.deleteById(id);
  }

  @Transactional
  public HospitalCapacityDto calculateCapacity(final UUID id) {
    final Hospital hospital = hospitalRepository.findById(id)
        .orElseThrow(() -> new NoSuchHospitalException(id));
    return hospitalCapacityDtoMapper
        .entityToDto(capacityService.calculateHospitalCapacity(hospital));
  }

}

