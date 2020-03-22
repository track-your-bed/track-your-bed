package de.wirvsvirus.trackyourbed.persistence;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import de.wirvsvirus.trackyourbed.entity.Ward;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface WardRepository extends CrudRepository<Ward, UUID> {
}
