package de.wirvsvirus.trackyourbed.resource;

import de.wirvsvirus.trackyourbed.HospitalService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewHospital;
import de.wirvsvirus.trackyourbed.dto.response.HospitalDto;
import java.net.URI;
import java.util.UUID;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalResource {

  private HospitalService hospitalService;

  @Inject
  public HospitalResource(final HospitalService hospitalService) {
    this.hospitalService = hospitalService;
  }

  /**
   * Creates a new hospital.
   *
   * @param createNewHospital
   *     the hospital to create.
   *
   * @return a {@link ResponseEntity}, representing the result.
   */
  @PostMapping
  public ResponseEntity<HospitalDto> createHospital(
      @RequestBody @Valid final CreateNewHospital createNewHospital) {
    final HospitalDto saved = hospitalService.createHospital(createNewHospital);
    final String createdLink = String.format("/api/hospitals/%s", saved.getId());
    // @formatter:off
    return ResponseEntity
        .created(URI.create(createdLink))
        .body(saved);
    // @formatter:on
  }

  @GetMapping("{id}")
  public ResponseEntity<HospitalDto> getHospital(@PathVariable("id") final UUID hospitalId) {
    return ResponseEntity.ok().body(hospitalService.getHospitalById(hospitalId));
  }
}
