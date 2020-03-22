package de.wirvsvirus.trackyourbed.resource;

import de.wirvsvirus.trackyourbed.DepartmentService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewDepartment;
import de.wirvsvirus.trackyourbed.dto.request.UpdateDepartment;
import de.wirvsvirus.trackyourbed.dto.response.DepartmentDto;
import java.net.URI;
import java.util.Collection;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
public class DepartmentResource {

  private final DepartmentService departmentService;

  public DepartmentResource(final DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @PostMapping
  public ResponseEntity<DepartmentDto> createDepartment(
      @RequestBody @Valid final CreateNewDepartment createNewDepartment) {
    final DepartmentDto saved = departmentService.createDepartment(createNewDepartment);
    final String createdLink = String.format("/api/hospitals/%s", saved.getId());
    return ResponseEntity.created(URI.create(createdLink)).body(saved);
  }

  @GetMapping
  public ResponseEntity<Collection<DepartmentDto>> getAllDepartments() {
    return ResponseEntity.ok(departmentService.getAllDepartments());
  }

  @GetMapping("{id}")
  public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(name = "id") final UUID id) {
    return ResponseEntity.ok(departmentService.getDepartmentById(id));
  }

  @PatchMapping("{id}")
  public ResponseEntity<DepartmentDto> patchDepartment(
      @PathVariable(name = "id") final UUID id,
      @RequestBody final UpdateDepartment updateDepartment) {
    return ResponseEntity.ok(departmentService.updateDepartment(id, updateDepartment));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteDepartment(@PathVariable("id") final UUID departmentId) {
    departmentService.deleteDepartment(departmentId);
    return ResponseEntity.ok().build();
  }

}
