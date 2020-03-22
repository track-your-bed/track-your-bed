package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewWard;
import de.wirvsvirus.trackyourbed.dto.request.mapper.CreateNewWardMapper;
import de.wirvsvirus.trackyourbed.dto.response.WardDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.WardDtoMapper;
import de.wirvsvirus.trackyourbed.entity.Department;
import de.wirvsvirus.trackyourbed.entity.Ward;
import de.wirvsvirus.trackyourbed.entity.WardType;
import de.wirvsvirus.trackyourbed.excpetion.NoSuchDepartmentExcpetion;
import de.wirvsvirus.trackyourbed.excpetion.NoSuchHospitalExcpetion;
import de.wirvsvirus.trackyourbed.excpetion.NoSuchWardException;
import de.wirvsvirus.trackyourbed.excpetion.NoSuchWardTypeExcpetion;
import de.wirvsvirus.trackyourbed.persistence.DepartmentRepository;
import de.wirvsvirus.trackyourbed.persistence.WardRepository;
import de.wirvsvirus.trackyourbed.persistence.WardTypeRepository;
import de.wirvsvirus.trackyourbed.dto.request.UpdateWard;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WardService {

  private final WardRepository wardRepository;
  private final DepartmentRepository departmentRepository;
  private final WardTypeRepository wardTypeRepository;
  private final CreateNewWardMapper createNewWardRequestMapper;
  private final WardDtoMapper wardDtoMapper;

  @Inject
  public WardService(
      final WardRepository wardRepository,
      final DepartmentRepository departmentRepository,
      final WardTypeRepository wardTypeRepository,
      final CreateNewWardMapper createNewWardRequestMapper,
      final WardDtoMapper wardDtoMapper) {
    this.wardRepository = wardRepository;
    this.departmentRepository = departmentRepository;
    this.wardTypeRepository = wardTypeRepository;
    this.createNewWardRequestMapper = createNewWardRequestMapper;
    this.wardDtoMapper = wardDtoMapper;
  }

  public WardDto createNewWard(final CreateNewWard createNewWard) {
    final Ward toSave = createNewWardRequestMapper.dtoToEntity(createNewWard);
    final Department department = departmentRepository.findById(createNewWard.getDepartmentId())
        .orElseThrow(NoSuchDepartmentExcpetion::new);
    // formatter:off
    final WardType wardType = wardTypeRepository
        .findByName(createNewWard.getWardTypeName())
        .orElseThrow(NoSuchWardTypeExcpetion::new);
    // formatter:on
    toSave.setDepartment(department);
    toSave.setWardType(wardType);
    final Ward saved = wardRepository.save(toSave);
    return wardDtoMapper.entityToDto(saved);
  }

  public Collection<WardDto> getAllWards() {
    final ArrayList<WardDto> result = new ArrayList<>();
    wardRepository.findAll()
        .forEach(statino -> result.add(wardDtoMapper.entityToDto(statino)));
    return result;
  }

  public WardDto getWardById(final UUID id) {
    final Ward fetched = wardRepository.findById(id)
        .orElseThrow(NoSuchWardException::new);
    return wardDtoMapper.entityToDto(fetched);
  }

  @Transactional
  public WardDto updateWard(final UUID id, final UpdateWard updateWard) {
    final Ward ward = wardRepository.findById(id).orElseThrow(NoSuchWardException::new);
    if (updateWard.getName() != null) {
      ward.setName(updateWard.getName());
    }
    if (updateWard.getDepartmentId() != null) {
      final Department department = departmentRepository.findById(updateWard.getDepartmentId())
          .orElseThrow(NoSuchHospitalExcpetion::new);
      ward.setDepartment(department);
    }
    if (updateWard.getWardTypeName() != null) {
      final WardType wardType =
          wardTypeRepository.findByName(updateWard.getWardTypeName())
              .orElseThrow(NoSuchWardTypeExcpetion::new);
      ward.setWardType(wardType);
    }
    return wardDtoMapper.entityToDto(ward);
  }
}
