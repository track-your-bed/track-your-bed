package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewHospital;
import de.wirvsvirus.trackyourbed.dto.request.mapper.CreateNewRequestMapper;
import de.wirvsvirus.trackyourbed.dto.response.HospitalDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.HospitalDtoMapper;
import de.wirvsvirus.trackyourbed.entity.Hospital;
import de.wirvsvirus.trackyourbed.persistence.HospitalRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;

@Component
public class HospitalService {

  private final HospitalRepository hospitalRepository;
  private final CreateNewRequestMapper createNewRequestMapper;
  private final HospitalDtoMapper hospitalDtoMapper;

  @Inject
  public HospitalService(
      final HospitalRepository hospitalRepository,
      final CreateNewRequestMapper createNewRequestMapper,
      final  HospitalDtoMapper hospitalDtoMapper) {
    this.hospitalRepository = hospitalRepository;
    this.createNewRequestMapper = createNewRequestMapper;
    this.hospitalDtoMapper = hospitalDtoMapper;
  }

  public HospitalDto createHospital(final CreateNewHospital createNewHospital) {
    final Hospital toSave = createNewRequestMapper.dtoToEntity(createNewHospital);
    final Hospital saved = hospitalRepository.save(toSave);
    return hospitalDtoMapper.entityToDto(saved);
  }

  public HospitalDto getHospitalById(final UUID hospitalId) {
    final Hospital retrieved = hospitalRepository.findById(hospitalId).get();
    return hospitalDtoMapper.entityToDto(retrieved);
  }
}

