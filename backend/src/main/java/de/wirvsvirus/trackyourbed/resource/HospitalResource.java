package de.wirvsvirus.trackyourbed.resource;

import de.wirvsvirus.trackyourbed.HospitalService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewHospital;
import de.wirvsvirus.trackyourbed.dto.request.mapper.CreateNewRequestMapper;
import de.wirvsvirus.trackyourbed.dto.response.HospitalDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.HospitalDtoMapper;
import de.wirvsvirus.trackyourbed.entity.Hospital;
import de.wirvsvirus.trackyourbed.persistence.HospitalRepository;
import java.net.URI;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalResource {

  @Inject
  private HospitalService hospitalService;

  @Inject
  private CreateNewRequestMapper createNewRequestMapper;

  @Inject
  private HospitalDtoMapper hospitalDtoMapper;

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
}
