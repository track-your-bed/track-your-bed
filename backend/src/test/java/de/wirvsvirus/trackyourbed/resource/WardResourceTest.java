package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.WardService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewWard;
import de.wirvsvirus.trackyourbed.dto.request.UpdateWard;
import de.wirvsvirus.trackyourbed.dto.response.WardCapacityDto;
import de.wirvsvirus.trackyourbed.dto.response.WardDto;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@DisplayName("Test for WardResource.")
class WardResourceTest {

  @Nested
  @DisplayName("Test calls to createNewWard.")
  class CreateNewWardTest {

    @Test
    @DisplayName("Should return a response entity containing the created link and wardDto when " +
        "called with createNewWard.")
    void ShouldReturnResponseEntityWithCreatedLinkAndWardDtoWhenCalledWithCreateNewWard() {
      //GIVEN
      final CreateNewWard createNewWard = mock(CreateNewWard.class);
      final UUID id = UUID.randomUUID();
      final WardDto wardDto = new WardDto();
      wardDto.setId(id);
      final WardService wardService = mock(WardService.class);
      when(wardService.createNewWard(any(CreateNewWard.class))).thenReturn(wardDto);

      final List<String> expectedLink = List.of(String.format("/api/wards/%s",id));

      //WHEN
      final ResponseEntity<WardDto> actual =
          new WardResource(wardService).createNewWard(createNewWard);

      //THEN
      assertEquals(HttpStatus.CREATED, actual.getStatusCode());
      assertEquals(expectedLink, actual.getHeaders().get("Location"));
      assertSame(wardDto, actual.getBody());

      verify(wardService).createNewWard(eq(createNewWard));
    }

  }

  @Nested
  @DisplayName("Test calls to getAllWards.")
  class GetAllWardsTest{

    @Test
    @DisplayName("Should return a ResponseEntity containing a collection of all wards with status " +
        "set to ok when called.")
    void shouldReturnResponseEntityContainingCollectionOfAllWardsWithStatusOkWhenCalled() {
      //GIVEN
      final WardDto wardOne = mock(WardDto.class);
      final WardDto wardTwo = mock(WardDto.class);
      final Collection<WardDto> allWards = List.of(
          wardOne,
          wardTwo
      );
      final WardService wardService = mock(WardService.class);
      when(wardService.getAllWards()).thenReturn(allWards);

      //WHEN
      final ResponseEntity<Collection<WardDto>> actual = new WardResource(wardService).getAllWards();

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(allWards, actual.getBody());
    }

  }

  @Nested
  @DisplayName("Test calls to getWardById.")
  class GetWardByIdTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing the correct WardDto with Status set to " +
        "OK when called with  UUID id.")
    void shouldReturnResponseEntityWithWardDtoAndStatusOkWhenCalledWithId() {
      //GIVEN
      final WardDto wardDto = new WardDto();
      final UUID id = UUID.randomUUID();
      wardDto.setId(id);
      final WardService wardService = mock(WardService.class);
      when(wardService.getWardById(any(UUID.class))).thenReturn(wardDto);

      //WHEN
      final ResponseEntity<WardDto> actual = new WardResource(wardService).getWardById(id);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(wardDto, actual.getBody());

      verify(wardService).getWardById(eq(id));
    }

  }

  @Nested
  @DisplayName("Test calls to patchWard.")
  class PatchWardTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing the updated wardDto with Status set to " +
        "OK when called with a UUID and an UpdateWard.")
    void shouldReturnResponseEntityContainingWardDtoWithStatusOkWhenCalledWithUuidAndUpdateWard() {
      //GIVEN
      final WardDto wardDto = new WardDto();
      final UpdateWard updateWard = new UpdateWard();
      final UUID id = UUID.randomUUID();
      final WardService wardService = mock(WardService.class);
      when(wardService.updateWard(any(UUID.class), any(UpdateWard.class))).thenReturn(wardDto);

      //WHEN
      final ResponseEntity<WardDto> actual = new WardResource(wardService).patchWard(id, updateWard);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(wardDto, actual.getBody());

      verify(wardService).updateWard(eq(id), eq(updateWard));
    }

  }

  @Nested
  @DisplayName("Test calls to deleteWard.")
  class DeleteWardTest {

    @Test
    @DisplayName("Should return a ResponseEntity with empty body and status set to OK when called " +
        "with Id.")
    void shouldReturnEmptyResponseEntityWithStatusOkWhenCalledWithId (){
      //GIVEN
      final WardService wardService = mock(WardService.class);
      final UUID id = UUID.randomUUID();

      final ResponseEntity<Void> expected = ResponseEntity.ok().build();

      //WHEN
      final ResponseEntity<Void> actual = new WardResource(wardService).deleteWard(id);

      //THEN
      assertEquals(expected, actual);
      assertEquals(HttpStatus.OK, actual.getStatusCode());

      verify(wardService).deleteWardById(eq(id));
    }

  }

  @Nested
  @DisplayName("Test calls to getCapacity.")
  class GetCapacityTest{

    @Test
    @DisplayName("Should return a ResponseEntity containing a WardCapacityDto with Status OK when " +
        "called with an Id.")
    void shouldReturnResponseEntityContainingWardCapacityDtoWithStatusOkWhenCalledWithId(){
      //GIVEN
      final WardCapacityDto wardCapacityDto = mock(WardCapacityDto.class);
      final UUID id = UUID.randomUUID();
      final WardService wardService = mock(WardService.class);
      when(wardService.calculateCapacity(any(UUID.class))).thenReturn(wardCapacityDto);

      //WHEN
      final ResponseEntity<WardCapacityDto> actual = new WardResource(wardService).getCapacity(id);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(wardCapacityDto, actual.getBody());

      verify(wardService).calculateCapacity(eq(id));
    }

  }

}