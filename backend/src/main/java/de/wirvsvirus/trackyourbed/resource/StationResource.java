package de.wirvsvirus.trackyourbed.resource;

import de.wirvsvirus.trackyourbed.StationService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewStation;
import de.wirvsvirus.trackyourbed.dto.request.UpdateStation;
import de.wirvsvirus.trackyourbed.dto.response.StationDto;
import java.net.URI;
import java.util.Collection;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stations")
public class StationResource {

  private final StationService stationService;

  public StationResource(final StationService stationService) {
    this.stationService = stationService;
  }

  @PostMapping
  public ResponseEntity<StationDto> createNewStation(
      @RequestBody @Valid final CreateNewStation createNewStation) {
    final StationDto saved = stationService.createNewStation(createNewStation);
    final String createdLink = String.format("/api/stations/%s", saved.getId());
    // @formatter:off
    return ResponseEntity
        .created(URI.create(createdLink))
        .body(saved);
    // @formatter:on
  }

  @GetMapping
  public ResponseEntity<Collection<StationDto>> getAllStations() {
    return ResponseEntity.ok(stationService.getAllStations());
  }

  @GetMapping("{id}")
  public ResponseEntity<StationDto> getStationById(@PathVariable("id") final UUID id) {
    return ResponseEntity.ok(stationService.getStationById(id));
  }

  @PatchMapping("{id}")
  public ResponseEntity<StationDto> patchStationDto(
      @PathVariable(name = "id") final UUID id,
      @RequestBody @Valid final UpdateStation updateStation) {
    return ResponseEntity.ok(stationService.updateStation(id, updateStation));
  }

}
