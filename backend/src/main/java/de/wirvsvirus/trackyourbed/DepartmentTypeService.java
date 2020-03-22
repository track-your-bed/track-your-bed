package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.response.DepartmentTypeDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.DepartmentTypeDtoMapper;
import de.wirvsvirus.trackyourbed.entity.DepartmentType;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchDepartmentTypeException;
import de.wirvsvirus.trackyourbed.persistence.DepartmentTypeRepository;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class DepartmentTypeService {

  private final DepartmentTypeRepository departmentTypeRepository;
  private final DepartmentTypeDtoMapper departmentTypeDtoMapper;

  @Inject
  public DepartmentTypeService(
      final DepartmentTypeRepository departmentTypeRepository,
      final DepartmentTypeDtoMapper departmentTypeDtoMapper) {
    this.departmentTypeRepository = departmentTypeRepository;
    this.departmentTypeDtoMapper = departmentTypeDtoMapper;
  }

  public DepartmentTypeDto getDepartmentTypeByName(final String name) {
    final DepartmentType fetched = departmentTypeRepository.findByName(name)
        .orElseThrow(() -> new NoSuchDepartmentTypeException(name));
    return departmentTypeDtoMapper.entityToDto(fetched);
  }

  public Collection<DepartmentTypeDto> getAllDepartmentTypes() {
    final ArrayList<DepartmentTypeDto> allDepartmentTypes = new ArrayList<>();
    departmentTypeRepository.findAll().forEach(entity -> allDepartmentTypes.add(departmentTypeDtoMapper.entityToDto(entity)));
    return allDepartmentTypes;
  }

}
