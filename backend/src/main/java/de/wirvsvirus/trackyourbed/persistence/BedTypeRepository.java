package de.wirvsvirus.trackyourbed.persistence;

import de.wirvsvirus.trackyourbed.entity.BedType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface BedTypeRepository extends CrudRepository<BedType, UUID> {
  Optional<BedType> getByName(String name);
}
