package de.wirvsvirus.trackyourbed.persistence;

import de.wirvsvirus.trackyourbed.entity.StationType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface StationTypeRepository extends CrudRepository<StationType, UUID> {
  Optional<StationType> getByName(String name);
}
