package de.wirvsvirus.trackyourbed.resource;

import de.wirvsvirus.trackyourbed.StationTypeService;
import de.wirvsvirus.trackyourbed.dto.response.StationTypeDto;
import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/stationTypes")
public class StationTypeResource {

  private final StationTypeService stationTypeService;

  @Inject
  public StationTypeResource(final StationTypeService stationTypeService) {
    this.stationTypeService = stationTypeService;
  }

  @GetMapping("{name}")
  public ResponseEntity<StationTypeDto> getStationTypeByName(
      @PathVariable("name") final String name) {
    return ResponseEntity.ok(stationTypeService.getStationTypeByName(name));
  }
}
