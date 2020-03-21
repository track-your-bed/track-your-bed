package de.wirvsvirus.trackyourbed.persistence;

import de.wirvsvirus.trackyourbed.entity.BedState;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface BedStateRepository extends CrudRepository<BedState, UUID> {
  Optional<BedState> getByName(String name);
}
