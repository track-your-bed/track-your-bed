package de.wirvsvirus.trackyourbed.persistence;

import de.wirvsvirus.trackyourbed.entity.WardType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface WardTypeRepository extends CrudRepository<WardType, UUID> {

  Optional<WardType> findByName(String name);

}
