package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.dto.response.BedStateDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.BedStateDtoMapper;
import de.wirvsvirus.trackyourbed.entity.BedState;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedStateException;
import de.wirvsvirus.trackyourbed.persistence.BedStateRepository;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class BedStateService {
  private final BedStateRepository bedStateRepository;
  private final BedStateDtoMapper bedStateDtoMapper;

  @Inject
  public BedStateService(
      final BedStateRepository bedStateRepository,
      final BedStateDtoMapper bedStateDtoMapper){
    this.bedStateRepository = bedStateRepository;
    this.bedStateDtoMapper = bedStateDtoMapper;
  }

  public BedStateDto getBedStateByName(final String name) {
      final BedState fetched = bedStateRepository.findByName(name)
          .orElseThrow(() -> new NoSuchBedStateException(name));
      return bedStateDtoMapper.entityToDto(fetched);
  }

  public Collection<BedStateDto> getAllBedStates() {
    final ArrayList<BedStateDto> allBedTypes = new ArrayList<>();
    bedStateRepository.findAll().forEach(entity -> allBedTypes.add(bedStateDtoMapper.entityToDto(entity)));
    return allBedTypes;
  }

}
