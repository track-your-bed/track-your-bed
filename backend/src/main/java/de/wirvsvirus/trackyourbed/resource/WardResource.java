package de.wirvsvirus.trackyourbed.resource;

import de.wirvsvirus.trackyourbed.WardService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewWard;
import de.wirvsvirus.trackyourbed.dto.request.UpdateWard;
import de.wirvsvirus.trackyourbed.dto.response.WardCapacityDto;
import de.wirvsvirus.trackyourbed.dto.response.WardDto;
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
@RequestMapping("/api/wards")
public class WardResource {

  private final WardService wardService;

  public WardResource(final WardService wardService) {
    this.wardService = wardService;
  }

  @PostMapping
  public ResponseEntity<WardDto> createNewWard(
      @RequestBody @Valid final CreateNewWard createNewWard) {
    final WardDto saved = wardService.createNewWard(createNewWard);
    final String createdLink = String.format("/api/wards/%s", saved.getId());
    // @formatter:off
    return ResponseEntity
        .created(URI.create(createdLink))
        .body(saved);
    // @formatter:on
  }

  @GetMapping
  public ResponseEntity<Collection<WardDto>> getAllWards() {
    return ResponseEntity.ok(wardService.getAllWards());
  }

  @GetMapping("{id}")
  public ResponseEntity<WardDto> getWardById(@PathVariable("id") final UUID id) {
    return ResponseEntity.ok(wardService.getWardById(id));
  }

  @PatchMapping("{id}")
  public ResponseEntity<WardDto> patchWard (
      @PathVariable(name = "id") final UUID id,
      @RequestBody @Valid final UpdateWard updateWard) {
    return ResponseEntity.ok(wardService.updateWard(id, updateWard));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteWard(@PathVariable("id") final UUID wardId) {
    wardService.deleteWardById(wardId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("{id}/capacity")
  public ResponseEntity<WardCapacityDto> getCapacity(@PathVariable("id") final UUID id) {
    return ResponseEntity.ok(wardService.calculateCapacity(id));
  }

}
