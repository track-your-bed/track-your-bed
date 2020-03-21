package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.response.BedTypeDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.BedTypeDtoMapper;
import de.wirvsvirus.trackyourbed.entity.BedType;
import de.wirvsvirus.trackyourbed.persistence.BedTypeRepository;
import java.util.NoSuchElementException;
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
    final BedType fetched = bedTypeRepository.getByName(name)
        .orElseThrow(NoSuchElementException::new);
    return bedTypeDtoMapper.entityToDto(fetched);
  }

}
