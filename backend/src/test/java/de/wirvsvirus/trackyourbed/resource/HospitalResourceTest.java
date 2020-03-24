package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.DepartmentService;
import de.wirvsvirus.trackyourbed.HospitalService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewDepartment;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewHospital;
import de.wirvsvirus.trackyourbed.dto.request.UpdateDepartment;
import de.wirvsvirus.trackyourbed.dto.request.UpdateHospital;
import de.wirvsvirus.trackyourbed.dto.response.DepartmentDto;
import de.wirvsvirus.trackyourbed.dto.response.HospitalDto;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

@DisplayName("Tests for HospitalResource")
class HospitalResourceTest {

  @Nested
  @DisplayName("Test calls to createHospital")
  class CreateHospitalTest {

    @Test
    @DisplayName("Should return a response entity containing the created link and hospitalDto" +
        "when called with createNewHospital")
    void ShouldReturnResponseEntityWithCreatedLinkAndHospitalDtoWhenCalledWithCreateNewHospital() {
      //GIVEN
      final HospitalService hospitalService = mock(HospitalService.class);
      final CreateNewHospital createNewHospital = mock(CreateNewHospital.class);
      final UUID id = UUID.randomUUID();
      final String createdLink = String.format("/api/hospitals/%s",id);
      final HospitalDto hospitalDto = new HospitalDto();
      hospitalDto.setId(id);
      when(hospitalService.createHospital(createNewHospital)).thenReturn(hospitalDto);
      final ResponseEntity<HospitalDto> expected = ResponseEntity
          .created(URI.create(createdLink))
          .body(hospitalDto);

      //WHEN
      final ResponseEntity<HospitalDto> actual = new HospitalResource(hospitalService).createHospital(createNewHospital);

      //THEN
      assertEquals(expected,actual);
    }
  }

  @Nested
  @DisplayName("Test calls to getAllHospitals")
  class GetAllHospitalsTest{
    @Test
    @DisplayName("Should return a ResponseEntity containing a collection of all hospitals" +
        "with status set to ok when called")
    void shouldReturnResponseEntityContainingCollectionOfAllHospitalsWithStatusOkWhenCalled() {
      //GIVEN
      final HospitalService hospitalService = mock(HospitalService.class);
      final HospitalDto hospitalOne = mock(HospitalDto.class);
      final HospitalDto hospitalTwo = mock(HospitalDto.class);
      final Collection<HospitalDto> allHospitals = List.of(
          hospitalOne,
          hospitalTwo
      );
      final ResponseEntity<Collection<HospitalDto>> expected = ResponseEntity.ok(allHospitals);
      when(hospitalService.getAllHospitals()).thenReturn(allHospitals);

      //WHEN
      final ResponseEntity<Collection<HospitalDto>> actual = new HospitalResource(hospitalService).getAllHospitals();
      //THEN
      assertEquals(actual, expected);

    }

  }

  @Nested
  @DisplayName("Test calls to getHospitalById")
  class GetHospitalTest {
    @Test
    @DisplayName("Should return a ResponseEntity containing the correct HospitalDto" +
        "with Status set to OK when called with  UUID id")
    void shouldReturnResponseEntityWithHospitalDtoAndStatusOkWhenCalledWithId() {
      //GIVEN
      final HospitalService hospitalService = mock(HospitalService.class);
      final HospitalDto hospitalDto = new HospitalDto();
      final UUID id = UUID.randomUUID();
      hospitalDto.setId(id);
      when(hospitalService.getHospitalById(id)).thenReturn(hospitalDto); //any
      final ResponseEntity<HospitalDto> expected = ResponseEntity.ok(hospitalDto);

      //WHEN
      final ResponseEntity<HospitalDto> actual = new HospitalResource(hospitalService).getHospital(id);
      //THEN
      assertEquals(expected, actual);
      //verify....
    }
  }

  @Nested
  @DisplayName("Test calls to patchHospital")
  class PatchHospitalTest {
    @Test
    @DisplayName("Should return a ResponseEntity containing the updated hospitalDto" +
        "with Status set to OK when called with a UUID and an UpdateHospital")
    void shouldReturnResponseEntityContainingHospitalDtoWithStatusOkWhenCalledWithUuidAndUpdateHospital() {
      //GIVEN
      final HospitalService hospitalService = mock(HospitalService.class);
      final HospitalDto hospitalDto = new HospitalDto();
      final UpdateHospital updateHospital = new UpdateHospital();
      final UUID id = UUID.randomUUID();
      when(hospitalService.updateHospital(id, updateHospital)).thenReturn(hospitalDto);
      final ResponseEntity<HospitalDto> expected = ResponseEntity.ok(hospitalDto);
      //WHEN
      final ResponseEntity<HospitalDto> actual = new HospitalResource(hospitalService).patchHospital(id, updateHospital);
      //THEN
      assertEquals(expected, actual);
    }

  }
}