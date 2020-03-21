package de.wirvsvirus.trackyourbed.persistence;

import de.wirvsvirus.trackyourbed.entity.Hospital;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface HospitalRepository extends CrudRepository<Hospital, UUID> {
}
