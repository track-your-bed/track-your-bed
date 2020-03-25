package de.wirvsvirus.trackyourbed.persistence;

import de.wirvsvirus.trackyourbed.entity.Bed;
import java.util.UUID;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface BedRepository extends CrudRepository<Bed, UUID> {
}
