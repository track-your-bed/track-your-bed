package de.wirvsvirus.trackyourbed.resource;

import de.wirvsvirus.trackyourbed.BedTypeService;
import de.wirvsvirus.trackyourbed.dto.response.BedTypeDto;
import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/bedTypes")
public class BedTypeResource {

  private final BedTypeService bedTypeService;

  @Inject
  public BedTypeResource(final BedTypeService bedTypeService) {
    this.bedTypeService = bedTypeService;
  }

  @GetMapping("{name}")
  public ResponseEntity<BedTypeDto> getBedTypeByName(
      @PathVariable("name") final String name) {
    return ResponseEntity.ok(bedTypeService.getBedTypeByName(name));
  }
}
