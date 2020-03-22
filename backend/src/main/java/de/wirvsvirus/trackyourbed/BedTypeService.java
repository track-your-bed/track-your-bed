package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.response.BedTypeDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.BedTypeDtoMapper;
import de.wirvsvirus.trackyourbed.entity.BedType;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedTypeException;
import de.wirvsvirus.trackyourbed.persistence.BedTypeRepository;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class BedTypeService {

  private final BedTypeRepository bedTypeRepository;
  private final BedTypeDtoMapper bedTypeDtoMapper;

  @Inject
  public BedTypeService(
      final BedTypeRepository bedTypeRepository,
      final BedTypeDtoMapper bedTypeDtoMapper) {
    this.bedTypeRepository = bedTypeRepository;
    this.bedTypeDtoMapper = bedTypeDtoMapper;
  }

  public BedTypeDto getBedTypeByName(final String name) {
    final BedType fetched = bedTypeRepository.findByName(name)
        .orElseThrow(() -> new NoSuchBedTypeException(name));
    return bedTypeDtoMapper.entityToDto(fetched);
  }

  public Collection<BedTypeDto> getAllBedTypes() {
    final ArrayList<BedTypeDto> allBedTypes = new ArrayList<>();
    bedTypeRepository.findAll().forEach(entity -> allBedTypes.add(bedTypeDtoMapper.entityToDto(entity)));
    return allBedTypes;
  }

}
