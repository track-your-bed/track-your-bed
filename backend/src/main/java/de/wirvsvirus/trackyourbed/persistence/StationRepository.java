package de.wirvsvirus.trackyourbed.persistence;

import org.springframework.data.repository.CrudRepository;
import de.wirvsvirus.trackyourbed.entity.Station;
import java.util.UUID;

public interface StationRepository extends CrudRepository<Station, UUID> {
}
