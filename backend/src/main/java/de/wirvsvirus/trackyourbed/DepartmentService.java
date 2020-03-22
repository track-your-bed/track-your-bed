package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewDepartment;
import de.wirvsvirus.trackyourbed.dto.request.UpdateDepartment;
import de.wirvsvirus.trackyourbed.dto.request.mapper.CreateNewDepartmentMapper;
import de.wirvsvirus.trackyourbed.dto.response.DepartmentCapacityDto;
import de.wirvsvirus.trackyourbed.dto.response.DepartmentDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.DepartmentCapacityDtoMapper;
import de.wirvsvirus.trackyourbed.dto.response.mapper.DepartmentDtoMapper;
import de.wirvsvirus.trackyourbed.entity.Department;
import de.wirvsvirus.trackyourbed.entity.DepartmentType;
import de.wirvsvirus.trackyourbed.entity.Hospital;
import de.wirvsvirus.trackyourbed.excpetion.dependency.HospitalMissingException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.InvalidDepartmentTypeException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchDepartmentException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchHospitalException;
import de.wirvsvirus.trackyourbed.persistence.DepartmentRepository;
import de.wirvsvirus.trackyourbed.persistence.DepartmentTypeRepository;
import de.wirvsvirus.trackyourbed.persistence.HospitalRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {

  private final DepartmentRepository departmentRepository;
  private final HospitalRepository hospitalRepository;
  private final DepartmentTypeRepository departmentTypeRepository;
  private final CapacityService capacityService;
  private final CreateNewDepartmentMapper createNewDepartmentMapper;
  private final DepartmentDtoMapper departmentDtoMapper;
  private final DepartmentCapacityDtoMapper departmentCapacityDtoMapper;

  @Inject
  public DepartmentService(
      final DepartmentRepository departmentRepository,
      final HospitalRepository hospitalRepository,
      final DepartmentTypeRepository departmentTypeRepository,
      final CapacityService capacityService,
      final CreateNewDepartmentMapper createNewDepartmentMapper,
      final DepartmentDtoMapper departmentDtoMapper,
      final DepartmentCapacityDtoMapper departmentCapacityDtoMapper) {
    this.departmentRepository = departmentRepository;
    this.hospitalRepository = hospitalRepository;
    this.departmentTypeRepository = departmentTypeRepository;
    this.capacityService = capacityService;
    this.createNewDepartmentMapper = createNewDepartmentMapper;
    this.departmentDtoMapper = departmentDtoMapper;
    this.departmentCapacityDtoMapper = departmentCapacityDtoMapper;
  }

  @Transactional
  public DepartmentDto createDepartment(final CreateNewDepartment createNewDepartment) {
    final Department toSave = createNewDepartmentMapper.dtoToEntity(createNewDepartment);

    final Hospital hospital = hospitalRepository.findById(createNewDepartment.getHospitalId())
        .orElseThrow(() -> new HospitalMissingException(createNewDepartment.getHospitalId()));
    toSave.setHospital(hospital);

    final String departmentTypeName = createNewDepartment.getDepartmentType();
    final DepartmentType departmentType = departmentTypeRepository.findByName(departmentTypeName)
        .orElseThrow(() -> new InvalidDepartmentTypeException(departmentTypeName));
    toSave.setDepartmentType(departmentType);

    final Department saved = departmentRepository.save(toSave);
    return departmentDtoMapper.entityToDto(saved);
  }

  public void deleteDepartment(final UUID departmentId) {
    departmentRepository.deleteById(departmentId);
  }

  @Transactional
  public Collection<DepartmentDto> getAllDepartments() {
    final ArrayList<DepartmentDto> result = new ArrayList<>();
    departmentRepository.findAll()
        .forEach(department -> result.add(departmentDtoMapper.entityToDto(department)));
    return result;
  }

  @Transactional
  public DepartmentDto getDepartmentById(final UUID id) {
    final Department retrieved = departmentRepository.findById(id)
        .orElseThrow(() -> new NoSuchDepartmentException(id));
    return departmentDtoMapper.entityToDto(retrieved);
  }

  @Transactional
  public DepartmentDto updateDepartment(final UUID id, final UpdateDepartment updateDepartment) {
    final Department department = departmentRepository.findById(id)
        .orElseThrow(() -> new NoSuchDepartmentException(id));

    final String name = updateDepartment.getName();
    if (name != null) {
      department.setName(name);
    }

    final UUID hospitalId = updateDepartment.getHospitalId();
    if (hospitalId != null) {
      final Hospital hospital =
          hospitalRepository.findById(hospitalId)
              .orElseThrow(() -> new NoSuchHospitalException(updateDepartment.getHospitalId()));
      department.setHospital(hospital);
    }

    return departmentDtoMapper.entityToDto(department);
  }

  public void deleteDepartmentById(final UUID departmentId) {
    departmentRepository.deleteById(departmentId);
  }

  @Transactional
  public DepartmentCapacityDto calculateCapacity(final UUID id) {
    final Department department = departmentRepository.findById(id)
        .orElseThrow(() -> new NoSuchDepartmentException(id));

    return departmentCapacityDtoMapper
        .entityToDto(capacityService.calculateDepartmentCapacity(department));
  }

}
