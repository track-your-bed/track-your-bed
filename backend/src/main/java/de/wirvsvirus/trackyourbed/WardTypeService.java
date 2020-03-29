package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.response.WardTypeDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.WardTypeDtoMapper;
import de.wirvsvirus.trackyourbed.entity.WardType;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchWardTypeException;
import de.wirvsvirus.trackyourbed.persistence.WardTypeRepository;
import java.util.Collection;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class WardTypeService {

  private final WardTypeRepository wardTypeRepository;
  private final WardTypeDtoMapper wardTypeDtoMapper;

  @Inject
  public WardTypeService(
      final WardTypeRepository wardTypeRepository,
      final WardTypeDtoMapper wardTypeDtoMapper) {
    this.wardTypeRepository = wardTypeRepository;
    this.wardTypeDtoMapper = wardTypeDtoMapper;
  }

  public WardTypeDto getWardTypeByName(final String name) {
    final WardType fetched = wardTypeRepository.findByName(name)
        .orElseThrow(() -> new NoSuchWardTypeException(name));
    return wardTypeDtoMapper.entityToDto(fetched);
  }

  public Collection<WardTypeDto> getAllWardTypes() {
    return wardTypeDtoMapper.entitiesToDtos(wardTypeRepository.findAll());
  }

}
