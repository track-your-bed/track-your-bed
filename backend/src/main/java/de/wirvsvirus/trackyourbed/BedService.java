package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewBed;
import de.wirvsvirus.trackyourbed.dto.request.UpdateBed;
import de.wirvsvirus.trackyourbed.dto.request.mapper.CreateNewBedMapper;
import de.wirvsvirus.trackyourbed.dto.response.BedDto;
import de.wirvsvirus.trackyourbed.dto.response.BedStateDto;
import de.wirvsvirus.trackyourbed.dto.response.BedTypeDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.BedDtoMapper;
import de.wirvsvirus.trackyourbed.dto.response.mapper.BedStateDtoMapper;
import de.wirvsvirus.trackyourbed.dto.response.mapper.BedTypeDtoMapper;
import de.wirvsvirus.trackyourbed.entity.Bed;
import de.wirvsvirus.trackyourbed.entity.BedState;
import de.wirvsvirus.trackyourbed.entity.BedType;
import de.wirvsvirus.trackyourbed.entity.Ward;
import de.wirvsvirus.trackyourbed.excpetion.dependency.InvalidBedStateException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.InvalidBedTypeException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.WardMissingException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedException;
import de.wirvsvirus.trackyourbed.persistence.BedRepository;
import de.wirvsvirus.trackyourbed.persistence.BedStateRepository;
import de.wirvsvirus.trackyourbed.persistence.BedTypeRepository;
import de.wirvsvirus.trackyourbed.persistence.WardRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BedService {

  private final BedRepository bedRepository;
  private final WardRepository wardRepository;
  private final BedTypeRepository bedTypeRepository;
  private final BedStateRepository bedStateRepository;
  private final CreateNewBedMapper createNewBedMapper;
  private final BedDtoMapper bedDtoMapper;
  private final BedStateDtoMapper bedStateDtoMapper;
  private final BedTypeDtoMapper bedTypeDtoMapper;

  @Inject
  public BedService(
      final BedRepository bedRepository,
      final WardRepository wardRepository,
      final BedTypeRepository bedTypeRepository,
      final BedStateRepository bedStateRepository,
      final CreateNewBedMapper createNewBedMapper,
      final BedDtoMapper bedDtoMapper,
      final BedStateDtoMapper bedStateDtoMapper,
      final BedTypeDtoMapper bedTypeDtoMapper) {
    this.bedRepository = bedRepository;
    this.wardRepository = wardRepository;
    this.bedTypeRepository = bedTypeRepository;
    this.bedStateRepository = bedStateRepository;
    this.createNewBedMapper = createNewBedMapper;
    this.bedDtoMapper = bedDtoMapper;
    this.bedStateDtoMapper = bedStateDtoMapper;
    this.bedTypeDtoMapper = bedTypeDtoMapper;
  }

  public BedDto createNewBed(final CreateNewBed createNewBed) {
    final Bed toSave = createNewBedMapper.dtoToEntity(createNewBed);
    toSave.setStateLastChanged(Instant.now());

    final UUID wardId = createNewBed.getWardId();
    final Ward ward = wardRepository.findById(wardId).orElseThrow(
        () -> new WardMissingException(wardId));
    toSave.setWard(ward);

    final String bedTypeName = createNewBed.getBedType();
    final BedType bedType = bedTypeRepository.findByName(bedTypeName)
        .orElseThrow(() -> new InvalidBedTypeException(bedTypeName));
    toSave.setBedType(bedType);

    final String bedStateName = createNewBed.getBedState();
    final BedState bedState = bedStateRepository.findByName(bedStateName)
        .orElseThrow(() -> new InvalidBedStateException(bedStateName));
    toSave.setBedState(bedState);

    final Bed saved = bedRepository.save(toSave);
    return bedDtoMapper.entityToDto(saved);
  }

  public Collection<BedDto> getAllBeds() {
    final ArrayList<BedDto> result = new ArrayList<>();
    bedRepository.findAll().forEach(bed -> result.add(bedDtoMapper.entityToDto(bed)));
    return result;
  }

  public BedDto getBedById(final UUID id) {
    final Bed retrieved = bedRepository.findById(id)
        .orElseThrow(() -> new NoSuchBedException(id));
    return bedDtoMapper.entityToDto(retrieved);
  }

  @Transactional
  public BedDto updateBed(final UUID id, final UpdateBed updateBed) {
    final Bed toUpdate = bedRepository.findById(id)
        .orElseThrow(() -> new NoSuchBedException(id));

    final String name = updateBed.getName();
    if (name != null) {
      toUpdate.setName(name);
    }

    final UUID wardId = updateBed.getWardId();
    if (wardId != null) {
      final Ward ward =
          wardRepository.findById(wardId).orElseThrow(() -> new WardMissingException(wardId));
      toUpdate.setWard(ward);
    }

    final String bedTypeName = updateBed.getBedType();
    if (bedTypeName != null) {
      final BedType bedType = bedTypeRepository.findByName(bedTypeName)
          .orElseThrow(() -> new InvalidBedTypeException(bedTypeName));
      toUpdate.setBedType(bedType);
    }

    final String bedStateName = updateBed.getBedState();
    if (bedStateName != null) {
      final BedState bedState = bedStateRepository.findByName(bedStateName)
          .orElseThrow(() -> new InvalidBedStateException(bedStateName));
      toUpdate.setBedState(bedState);
    }

    final Bed updated = bedRepository.save(toUpdate);
    return bedDtoMapper.entityToDto(updated);
  }

  public void deleteBedById(final UUID id) {
    bedRepository.deleteById(id);
  }

  @Transactional
  public BedStateDto updateState(final UUID id, final String bedStateName) {
    final Bed toUpdate = bedRepository.findById(id).orElseThrow(() -> new NoSuchBedException(id));

    final BedState bedState = bedStateRepository.findByName(bedStateName)
        .orElseThrow(() -> new InvalidBedStateException(bedStateName));

    toUpdate.setBedState(bedState);
    final Bed updated = bedRepository.save(toUpdate);
    return bedStateDtoMapper.entityToDto(updated.getBedState());
  }

  @Transactional
  public BedTypeDto updateType(final UUID id, final String bedTypeName) {
    final Bed toUpdate = bedRepository.findById(id).orElseThrow(() -> new NoSuchBedException(id));

    final BedType bedType = bedTypeRepository.findByName(bedTypeName)
        .orElseThrow(() -> new InvalidBedTypeException(bedTypeName));

    toUpdate.setBedType(bedType);
    final Bed updated = bedRepository.save(toUpdate);
    return bedTypeDtoMapper.entityToDto(updated.getBedType());
  }
}
