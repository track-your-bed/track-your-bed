package de.wirvsvirus.trackyourbed.persistence;

import de.wirvsvirus.trackyourbed.entity.DepartmentType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.data.repository.CrudRepository;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface DepartmentTypeRepository extends CrudRepository<DepartmentType, UUID> {
  Optional<DepartmentType> findByName(String name);

}
