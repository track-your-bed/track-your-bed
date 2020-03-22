package de.wirvsvirus.trackyourbed.resource;

import de.wirvsvirus.trackyourbed.BedService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewBed;
import de.wirvsvirus.trackyourbed.dto.request.UpdateBed;
import de.wirvsvirus.trackyourbed.dto.request.UpdateBedBedStateDto;
import de.wirvsvirus.trackyourbed.dto.request.UpdateBedBedTypeDto;
import de.wirvsvirus.trackyourbed.dto.response.BedDto;
import java.net.URI;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/beds")
public class BedResource {

  private final BedService bedService;

  @Inject
  public BedResource(final BedService bedService) {
    this.bedService = bedService;
  }

  @PostMapping
  public ResponseEntity<BedDto> createBed(@RequestBody @Valid final CreateNewBed createNewBed) {
    final BedDto saved = bedService.createNewBed(createNewBed);
    final String createdLink = String.format("/api/beds/%s", saved.getName());
    // @formatter:off
    return ResponseEntity
        .created(URI.create(createdLink))
        .body(saved);
    // @formatter:on
  }

  @GetMapping
  public ResponseEntity<Collection<BedDto>> getAllBeds() {
    return ResponseEntity.ok(bedService.getAllBeds());
  }

  @GetMapping("{id}")
  public ResponseEntity<BedDto> getBedById(@PathVariable("id") final UUID id) {
    return ResponseEntity.ok(bedService.getBedById(id));
  }

  @PatchMapping("{id}")
  public ResponseEntity<BedDto> patchBed(
      @PathVariable("id") final UUID id, @RequestBody final
      UpdateBed updateBed) {
    return ResponseEntity.ok(bedService.updateBed(id, updateBed));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteBed(@PathVariable("id") final UUID id) {
    bedService.deleteBedById(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("{id}")
  public ResponseEntity<BedDto> updateBedState(
      @PathVariable("id") final UUID id,
      @RequestBody final UpdateBedBedStateDto updateBedBedStateDto) {
    return ResponseEntity.ok(bedService.updateState(id, updateBedBedStateDto.getBedState()));
  }

  @PutMapping("{id}")
  public ResponseEntity<BedDto> updateBedType(
      @PathVariable("id") final UUID id,
      @RequestBody final UpdateBedBedTypeDto updateBedBedTypeDto) {
    return ResponseEntity.ok(bedService.updateType(id, updateBedBedTypeDto.getBedType()));
  }
}
