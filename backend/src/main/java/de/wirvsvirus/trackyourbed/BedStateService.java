package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.entity.BedState;
import de.wirvsvirus.trackyourbed.dto.response.BedStateDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.BedStateDtoMapper;
import de.wirvsvirus.trackyourbed.persistence.BedStateRepository;
import java.util.NoSuchElementException;
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
      final BedState fetched = bedStateRepository.getByName(name)
          .orElseThrow(NoSuchElementException::new);
      return bedStateDtoMapper.entityToDto(fetched);

  }
}
