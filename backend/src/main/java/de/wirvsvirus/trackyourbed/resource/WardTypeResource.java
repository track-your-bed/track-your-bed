package de.wirvsvirus.trackyourbed.resource;

import de.wirvsvirus.trackyourbed.WardTypeService;
import de.wirvsvirus.trackyourbed.dto.response.WardTypeDto;
import java.util.Collection;
import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/wardTypes")
public class WardTypeResource {

  private final WardTypeService wardTypeService;

  @Inject
  public WardTypeResource(final WardTypeService wardTypeService) {
    this.wardTypeService = wardTypeService;
  }

  @GetMapping
  public ResponseEntity<Collection<WardTypeDto>> getAllWardTypes() {
    return ResponseEntity.ok(wardTypeService.getAllWardTypes());
  }

  @GetMapping("{name}")
  public ResponseEntity<WardTypeDto> getWardTypeByName(
      @PathVariable("name") final String name) {
    return ResponseEntity.ok(wardTypeService.getWardTypeByName(name));
  }
}
