package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.BedService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewBed;
import de.wirvsvirus.trackyourbed.dto.request.UpdateBed;
import de.wirvsvirus.trackyourbed.dto.response.BedDto;
import de.wirvsvirus.trackyourbed.dto.response.BedStateDto;
import de.wirvsvirus.trackyourbed.dto.response.BedTypeDto;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@DisplayName("Tests for BedResource.")
class BedResourceTest {

  @Nested
  @DisplayName("Test calls to bedTest.")
  class CreateBedTest {

    @Test
    @DisplayName("Should return a response entity containing the created link and bedDto when " +
        "called with createNewBed.")
    void ShouldReturnResponseEntityWithCreatedLinkAndBedDtoWhenCalledWithCreateNewBed() {
      //GIVEN
      final CreateNewBed createNewBed = mock(CreateNewBed.class);
      final UUID id = UUID.randomUUID();
      // TODO: refactor to chaining setters after merge
      final BedDto bedDto = new BedDto();
      bedDto.setId(id);
      final BedService bedService = mock(BedService.class);
      when(bedService.createNewBed(any(CreateNewBed.class))).thenReturn(bedDto);

      final List<String> expectedLink = List.of(String.format("/api/beds/%s", id));

      //WHEN
      final ResponseEntity<BedDto> actual = new BedResource(bedService).createBed(createNewBed);

      //THEN
      assertEquals(HttpStatus.CREATED, actual.getStatusCode());
      assertEquals(expectedLink, actual.getHeaders().get("Location"));
      assertSame(bedDto, actual.getBody());

      verify(bedService).createNewBed(eq(createNewBed));
    }

  }

  @Nested
  @DisplayName("Test calls to getAllBeds.")
  class GetAllBedsTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing a collection of all beds with status " +
        "set to ok when called.")
    void shouldReturnResponseEntityContainingCollectionOfAllBedsWithStatusOkWhenCalled() {
      //GIVEN
      final BedDto bedOne = new BedDto();
      final BedDto bedTwo = new BedDto();
      final Collection<BedDto> allBeds = List.of(
          bedOne,
          bedTwo
      );
      final BedService bedService = mock(BedService.class);
      when(bedService.getAllBeds()).thenReturn(allBeds);

      //WHEN
      final ResponseEntity<Collection<BedDto>> actual = new BedResource(bedService).getAllBeds();

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(allBeds, actual.getBody());
    }

  }

  @Nested
  @DisplayName("Test calls to getBedById.")
  class getBedByIdTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing the correct BedDto with Status set " +
        "to OK when called with  UUID id.")
    void shouldReturnResponseEntityWithBedDtoAndStatusOkWhenCalledWithId() {
      //GIVEN
      final UUID id = UUID.randomUUID();
      // TODO: refactor to chaining setters after merge
      final BedDto bedDto = new BedDto();
      bedDto.setId(id);
      final BedService bedService = mock(BedService.class);
      when(bedService.getBedById(any(UUID.class))).thenReturn(bedDto);

      //WHEN
      final ResponseEntity<BedDto> actual = new BedResource(bedService).getBedById(id);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(bedDto, actual.getBody());

      verify(bedService).getBedById(eq(id));
    }

  }

  @Nested
  @DisplayName("Test calls to patchBed.")
  class PatchBedTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing the updated bedDto with Status se " +
        "to OK when called with a UUID and an UpdateBed.")
    void shouldReturnResponseEntityContainingBedDtoWithStatusOkWhenCalledWithUuidAndUpdateBed() {
      //GIVEN
      final UpdateBed updateBed = new UpdateBed();
      final BedDto expectedBody = new BedDto();
      final BedService bedService = mock(BedService.class);
      when(bedService.updateBed(any(UUID.class), any(UpdateBed.class))).thenReturn(expectedBody);

      final UUID id = UUID.randomUUID();

      //WHEN
      final ResponseEntity<BedDto> actual = new BedResource(bedService).patchBed(id, updateBed);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(expectedBody, actual.getBody());

      verify(bedService).updateBed(eq(id), eq(updateBed));
    }

  }


  @Nested
  @DisplayName("Test calls to deleteBed.")
  class DeleteBedTest {

    @Test
    @DisplayName("Should return a ResponseEntity with empty body and status set to OK when " +
        "called with Id.")
    void shouldReturnEmptyResponseEntityWithStatusOkWhenCalledWithId (){
      //GIVEN
      final UUID id = UUID.randomUUID();
      final BedService bedService = mock(BedService.class);

      //WHEN
      final ResponseEntity<Void> actual = new BedResource(bedService).deleteBed(id);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());

      verify(bedService).deleteBedById(eq(id));
    }

  }

  @Nested
  @DisplayName("Test calls to updateBedState.")
  class UpdateBedStateTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing the updated BedStateDto and Status " +
        " set to Ok when called with id and updateBedState.")
    void shouldReturnResponseEntityWithUpdatedBedStateDtoAndStatusOkWhenCalledWithIdAndUpdateBedState(){
      //GiVEN
      final UUID id = UUID.randomUUID();
      final String name = "name";
      // TODO: refactor to chaining setters after merge
      final BedStateDto bedState= new BedStateDto();
      bedState.setName(name);
      final BedStateDto bedStateDto = new BedStateDto();
      bedStateDto.setName(name);
      final BedService bedService = mock(BedService.class);
      when(bedService.updateState(any(UUID.class), anyString())).thenReturn(bedStateDto);

      //WHEN
      final ResponseEntity<BedStateDto> actual =
          new BedResource(bedService).updateBedState(id, bedState);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(bedStateDto, actual.getBody());

      verify(bedService).updateState(eq(id), eq(name));
    }

  }

  @Nested
  @DisplayName("Test calls to updateBedType.")
  class UpdateBedTypeTest {

    @Test
    @DisplayName("Should return ResponseEntity containing the updated BedTypeDto and Status set" +
        "to Ok when called with id and BedTypeDto.")
    void shouldReturnResponseEntityWithUpdatedBedTypeDtoStatusOkWhenCalledWithIdAndBedTypeDto (){
      //GiVEN
      final UUID id = UUID.randomUUID();
      final String name = "name";
      // TODO: refactor to chaining setters after merge
      final BedTypeDto bedType= new BedTypeDto();
      bedType.setName(name);
      final BedTypeDto bedTypeDto = new BedTypeDto();
      bedTypeDto.setName(name);
      final BedService bedService = mock(BedService.class);
      when(bedService.updateType(any(UUID.class), anyString())).thenReturn(bedTypeDto);

      //WHEN
      final ResponseEntity<BedTypeDto> actual =
          new BedResource(bedService).updateBedType(id, bedType);

      //THEN
      assertEquals(HttpStatus.OK , actual.getStatusCode());
      assertSame(bedTypeDto, actual.getBody());

      verify(bedService).updateType(eq(id), eq(name));
    }

  }

}