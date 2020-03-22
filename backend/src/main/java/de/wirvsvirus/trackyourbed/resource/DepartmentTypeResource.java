package de.wirvsvirus.trackyourbed.resource;

import de.wirvsvirus.trackyourbed.DepartmentTypeService;
import de.wirvsvirus.trackyourbed.dto.response.DepartmentTypeDto;
import java.util.Collection;
import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/departmentTypes")
public class DepartmentTypeResource {

  private final DepartmentTypeService departmentTypeService;

  @Inject
  public DepartmentTypeResource(final DepartmentTypeService departmentTypeService) {
    this.departmentTypeService = departmentTypeService;
  }

  @GetMapping
  public ResponseEntity<Collection<DepartmentTypeDto>> getAllDepartmentTypes() {
    return ResponseEntity.ok(departmentTypeService.getAllDepartmentTypes());
  }

  @GetMapping("{name}")
  public ResponseEntity<DepartmentTypeDto> getDepartmentTypeByName(
      @PathVariable("name") final String name) {
    return ResponseEntity.ok(departmentTypeService.getDepartmentTypeByName(name));
  }

}
