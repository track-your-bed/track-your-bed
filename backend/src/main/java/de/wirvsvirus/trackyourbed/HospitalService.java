package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewHospital;
import de.wirvsvirus.trackyourbed.dto.request.UpdateHospital;
import de.wirvsvirus.trackyourbed.dto.request.mapper.CreateNewHospitalMapper;
import de.wirvsvirus.trackyourbed.dto.response.HospitalDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.HospitalDtoMapper;
import de.wirvsvirus.trackyourbed.entity.Hospital;
import de.wirvsvirus.trackyourbed.persistence.HospitalRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
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

  /**
   * Instantiates a new Hospital service.
   *
   * @param hospitalRepository
   *     the hospital repository.
   * @param createNewHospitalMapper
   *     the create new request mapper.
   * @param hospitalDtoMapper
   *     the hospital dto mapper.
   */
  @Inject
  public HospitalService(final HospitalRepository hospitalRepository,
      final CreateNewHospitalMapper createNewHospitalMapper,
      final HospitalDtoMapper hospitalDtoMapper) {
    this.hospitalRepository = hospitalRepository;
    this.createNewHospitalMapper = createNewHospitalMapper;
    this.hospitalDtoMapper = hospitalDtoMapper;
  }

  /**
   * Creates and persists a new {@code Hospital} from a {@link CreateNewHospital} request.
   *
   * @param createNewHospital
   *     the new hospital to create.
   *
   * @return a {@link HospitalDto}, representing the newly created hospital.
   */
  public HospitalDto createHospital(final CreateNewHospital createNewHospital) {
    final Hospital toSave = createNewHospitalMapper.dtoToEntity(createNewHospital);
    final Hospital saved = hospitalRepository.save(toSave);
    return hospitalDtoMapper.entityToDto(saved);
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
        hospitalRepository.findById(hospitalId).orElseThrow(NoSuchElementException::new);
    return hospitalDtoMapper.entityToDto(retrieved);
  }

  public Collection<HospitalDto> getAllHospitals() {
    final ArrayList<HospitalDto> allHospitals = new ArrayList<>();
    hospitalRepository.findAll()
        .forEach(entity -> allHospitals.add(hospitalDtoMapper.entityToDto(entity)));
    return allHospitals;
  }

  @Transactional
  public HospitalDto updateHospital(final UUID hospitalId, final UpdateHospital updateHospital) {
    final Hospital toBeUpdated =
        hospitalRepository.findById(hospitalId).orElseThrow(NoSuchElementException::new);

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
}

