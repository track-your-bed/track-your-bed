package de.wirvsvirus.trackyourbed.resource;

import de.wirvsvirus.trackyourbed.BedStateService;
import de.wirvsvirus.trackyourbed.dto.response.BedStateDto;
import java.util.Collection;
import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/bedStates")
public class BedStateResource {

  private final BedStateService bedStateService;

  @Inject
  public BedStateResource(final BedStateService bedStateService){
    this.bedStateService = bedStateService;
  }

  @GetMapping()
  public ResponseEntity<Collection<BedStateDto>> getAllBedStates() {
    return ResponseEntity.ok(bedStateService.getAllBedStates());
  }

  @GetMapping("{name}")
public ResponseEntity<BedStateDto> getBedStateByName(
    @PathVariable("name") final String name){
  return ResponseEntity.ok(bedStateService.getBedStateByName(name));
}

}
