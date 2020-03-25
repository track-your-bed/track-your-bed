package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.HospitalService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewHospital;
import de.wirvsvirus.trackyourbed.dto.request.UpdateHospital;
import de.wirvsvirus.trackyourbed.dto.response.HospitalCapacityDto;
import de.wirvsvirus.trackyourbed.dto.response.HospitalDto;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@DisplayName("Tests for HospitalResource.")
class HospitalResourceTest {

  @Nested
  @DisplayName("Test calls to createHospital.")
  class CreateHospitalTest {

    @Test
    @DisplayName("Should return a response entity containing the created link and hospitalDto when " +
        "called with createNewHospital.")
    void ShouldReturnResponseEntityWithCreatedLinkAndHospitalDtoWhenCalledWithCreateNewHospital() {
      //GIVEN
      final CreateNewHospital createNewHospital = mock(CreateNewHospital.class);
      final UUID id = UUID.randomUUID();
      final HospitalDto hospitalDto = new HospitalDto();
      hospitalDto.setId(id);
      final HospitalService hospitalService = mock(HospitalService.class);
      when(hospitalService.createHospital(any(CreateNewHospital.class))).thenReturn(hospitalDto);

      final List<String> expectedLink = List.of(String.format("/api/hospitals/%s",id));

      //WHEN
      final ResponseEntity<HospitalDto> actual =
          new HospitalResource(hospitalService).createHospital(createNewHospital);

      //THEN
      assertEquals(HttpStatus.CREATED, actual.getStatusCode());
      assertEquals(expectedLink, actual.getHeaders().get("Location"));
      assertSame(hospitalDto, actual.getBody());

      verify(hospitalService).createHospital(eq(createNewHospital));
    }

  }

  @Nested
  @DisplayName("Test calls to getAllHospitals.")
  class GetAllHospitalsTest{

    @Test
    @DisplayName("Should return a ResponseEntity containing a collection of all hospitals with " +
        "status set to ok when called.")
    void shouldReturnResponseEntityContainingCollectionOfAllHospitalsWithStatusOkWhenCalled() {
      //GIVEN
      final HospitalDto hospitalOne = mock(HospitalDto.class);
      final HospitalDto hospitalTwo = mock(HospitalDto.class);
      final Collection<HospitalDto> allHospitals = List.of(
          hospitalOne,
          hospitalTwo
      );
      final HospitalService hospitalService = mock(HospitalService.class);
      when(hospitalService.getAllHospitals()).thenReturn(allHospitals);

      //WHEN
      final ResponseEntity<Collection<HospitalDto>> actual =
          new HospitalResource(hospitalService).getAllHospitals();

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(allHospitals, actual.getBody());
    }

  }

  @Nested
  @DisplayName("Test calls to getHospitalById.")
  class GetHospitalTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing the correct HospitalDto with Status set " +
        "to OK when called with  UUID id.")
    void shouldReturnResponseEntityWithHospitalDtoAndStatusOkWhenCalledWithId() {
      //GIVEN
      final HospitalDto hospitalDto = new HospitalDto();
      final UUID id = UUID.randomUUID();
      hospitalDto.setId(id);
      final HospitalService hospitalService = mock(HospitalService.class);
      when(hospitalService.getHospitalById(any(UUID.class))).thenReturn(hospitalDto);

      //WHEN
      final ResponseEntity<HospitalDto> actual =
          new HospitalResource(hospitalService).getHospital(id);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(hospitalDto, actual.getBody());

      verify(hospitalService).getHospitalById(eq(id));
    }

  }

  @Nested
  @DisplayName("Test calls to patchHospital.")
  class PatchHospitalTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing the updated hospitalDto with Status set " +
        "to OK when called with a UUID and an UpdateHospital.")
    void shouldReturnResponseEntityContainingHospitalDtoWithStatusOkWhenCalledWithUuidAndUpdateHospital() {
      //GIVEN
      final HospitalDto hospitalDto = new HospitalDto();
      final UpdateHospital updateHospital = new UpdateHospital();
      final UUID id = UUID.randomUUID();
      final HospitalService hospitalService = mock(HospitalService.class);
      when(hospitalService.updateHospital(any(UUID.class), any(UpdateHospital.class))).
          thenReturn(hospitalDto);

      //WHEN
      final ResponseEntity<HospitalDto> actual =
          new HospitalResource(hospitalService).patchHospital(id, updateHospital);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(hospitalDto, actual.getBody());

      verify(hospitalService).updateHospital(eq(id), eq(updateHospital));
    }

  }

  @Nested
  @DisplayName("Test calls to deleteHospital.")
  class DeleteHospitalTest {

    @Test
    @DisplayName("Should return a ResponseEntity with empty body and status set to OK when called " +
        "with Id.")
    void shouldReturnEmptyResponseEntityWithStatusOkWhenCalledWithId (){
      //GIVEN
      final HospitalService hospitalService = mock(HospitalService.class);
      final UUID id = UUID.randomUUID();

      final ResponseEntity<Void> expected = ResponseEntity.ok().build();

      //WHEN
      final ResponseEntity<Void> actual =
          new HospitalResource(hospitalService).deleteHospital(id);

      //THEN
      assertEquals(expected, actual);
      assertEquals(HttpStatus.OK, actual.getStatusCode());

      verify(hospitalService).deleteHospitalById(eq(id));
    }
  }

  @Nested
  @DisplayName("Test calls to getCapacity.")
  class GetCapacityTest{

    @Test
    @DisplayName("Should return a ResponseEntity containing a HospitalCapacityDto with Status OK " +
        "when called with an Id.")
    void shouldReturnResponseEntityContainingHospitalCapacityDtoWithStatusOkWhenCalledWithId(){
      //GIVEN
      final HospitalCapacityDto hospitalCapacityDto = mock(HospitalCapacityDto.class);
      final UUID id = UUID.randomUUID();
      final HospitalService hospitalService = mock(HospitalService.class);
      when(hospitalService.calculateCapacity(any(UUID.class))).thenReturn(hospitalCapacityDto);

      //WHEN
      final ResponseEntity<HospitalCapacityDto> actual =
          new HospitalResource(hospitalService).getCapacity(id);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(hospitalCapacityDto, actual.getBody());

      verify(hospitalService).calculateCapacity(eq(id));
    }

  }

}