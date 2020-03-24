package de.wirvsvirus.trackyourbed.resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.BedService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewBed;
import de.wirvsvirus.trackyourbed.dto.request.UpdateBed;
import de.wirvsvirus.trackyourbed.dto.response.BedDto;
import de.wirvsvirus.trackyourbed.dto.response.BedStateDto;
import de.wirvsvirus.trackyourbed.dto.response.BedTypeDto;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@DisplayName("Tests for BedResource")
class BedResourceTest {

  @Nested
  @DisplayName("Test calls to bedTest")
  class CreateBedTest {

    @Test
    @DisplayName("Should return a response entity containing the created link and bedDto when " +
        "called with createNewbed")
    void ShouldReturnResponseEntityWithCreatedLinkAndBedDtoWhenCalledWithCreateNewBed() {
      //GIVEN
      final BedService bedService = mock(BedService.class);
      final CreateNewBed createNewBed = mock(CreateNewBed.class);
      final UUID id = UUID.randomUUID();
      final BedDto bedDto = new BedDto();
      bedDto.setId(id);
      final List<String> expectedLink = List.of(String.format("/api/beds/%s", id));
      when(bedService.createNewBed(createNewBed)).thenReturn(bedDto);

      //WHEN
      final ResponseEntity<BedDto> actual = new BedResource(bedService).createBed(createNewBed);

      //THEN
      assertEquals(HttpStatus.CREATED, actual.getStatusCode());
      assertEquals(expectedLink, actual.getHeaders().get("Location"));
      assertSame(bedDto, actual.getBody());
    }

  }

  @Nested
  @DisplayName("Test calls to getAllBeds")
  class GetAllBedsTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing a collection of all beds" +
        "with status set to ok when called")
    void shouldReturnResponseEntityContainingCollectionOfAllBedsWithStatusOkWhenCalled() {
      //GIVEN
      final BedDto bedOne = mock(BedDto.class);
      final BedDto bedTwo = mock(BedDto.class);
      final Collection<BedDto> allBeds = List.of(
          bedOne,
          bedTwo
      );
      final BedService bedService = mock(BedService.class);
      when(bedService.getAllBeds()).thenReturn(allBeds);

      final ResponseEntity<Collection<BedDto>> expected = ResponseEntity.ok(allBeds);

      //WHEN
      final ResponseEntity<Collection<BedDto>> actual = new BedResource(bedService).getAllBeds();
      //THEN
      // TODO: other way around
      assertEquals(actual, expected);
    }
  }

  @Nested
  @DisplayName("Test calls to getBedById")
  class getBedByIdTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing the correct BedDto" +
        "with Status set to OK when called with  UUID id")
    void shouldReturnResponseEntityWithBedDtoAndStatusOkWhenCalledWithId() {
      //GIVEN
      final BedDto bedDto = new BedDto();
      final UUID id = UUID.randomUUID();
      bedDto.setId(id);
      final BedService bedService = mock(BedService.class);
      when(bedService.getBedById(any(UUID.class))).thenReturn(bedDto); //any and optional.of missing

      final ResponseEntity<BedDto> expected = ResponseEntity.ok(bedDto);

      //WHEN
      final ResponseEntity<BedDto> actual = new BedResource(bedService).getBedById(id);

      //THEN
      assertEquals(expected, actual);

      verify(bedService).getBedById(eq(id));
    }

  }

  @Nested
  @DisplayName("Test calls to patchBed")
  class PatchBedTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing the updated bedDto" +
        "with Status set to OK when called with a UUID and an UpdateBed")
    void shouldReturnResponseEntityContainingBedDtoWithStatusOkWhenCalledWithUuidAndUpdateBed() {
      //GIVEN
      final UpdateBed updateBed = new UpdateBed();
      final UUID id = UUID.randomUUID();
      final BedService bedService = mock(BedService.class);
      final BedDto expectedBody = new BedDto();
      when(bedService.updateBed(id, updateBed)).thenReturn(expectedBody);


      //WHEN
      final ResponseEntity<BedDto> actual = new BedResource(bedService).patchBed(id, updateBed);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(expectedBody, actual.getBody());

      verify(bedService).updateBed(eq(id), eq(updateBed));
    }

  }


  @Nested
  @DisplayName("Test calls to deleteBed")
  class DeleteBedTest {

    @Test
    @DisplayName("Should return a ResponseEntity with empty body" +
        "and status set to OK when called with Id ")
    void shouldReturnEmptyResponseEntityWithStatusOkWhenCalledWithId (){
      //GIVEN
      final UUID id = UUID.randomUUID();
      final ResponseEntity<Void> expected = ResponseEntity.ok().build();
      final BedService bedService = mock(BedService.class);

      //WHEN
      final ResponseEntity<Void> actual = new BedResource(bedService).deleteBed(id);

      //THEN
      assertEquals(expected,actual);

      verify(bedService).deleteBedById(eq(id));
    }

  }

  @Nested
  @DisplayName("Test calls to updateBedState")
  class UpdateBedStateTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing the updated BedStateDto and Status set to OK" +
        "when called with id and updateBedState")
    void shouldReturnResponseEntityWithUpdatedBedStateDtoAndStatusOkWhenCalledWithIdAndUpdateBedState(){
      //GiVEN
      final UUID id = UUID.randomUUID();
      final String name = "name";
      final BedStateDto bedState= new BedStateDto();
      bedState.setName(name);
      final BedStateDto bedStateDto = new BedStateDto();
      bedStateDto.setName(name);
      final BedService bedService = mock(BedService.class);
      when(bedService.updateState(id,bedState.getName())).thenReturn(bedStateDto);
      final ResponseEntity<BedStateDto> expected = ResponseEntity.ok(bedStateDto);

      //WHEN
      final ResponseEntity<BedStateDto> actual = new BedResource(bedService).updateBedState(id,bedState);

      //THEN
      assertEquals(expected,actual);
    }
  }

  @Nested
  @DisplayName("Test calls to updateBedType")
  class UpdateBedType {

    @Test
    @DisplayName("Should return ResponseEntity containing the updated BedTypeDto and Status set to OK" +
        "when called with id and BedTypeDto")
    void shouldReturnResponseEntityWithUpdatedBedTypeDtoStatusOkWhenCalledWithIdAndBedTypeDto (){
      //GiVEN
      final BedService bedService = mock(BedService.class);
      final UUID id = UUID.randomUUID();
      final String name = "name";
      final BedTypeDto bedType= new BedTypeDto();
      bedType.setName(name);
      final BedTypeDto bedTypeDto = new BedTypeDto();
      bedTypeDto.setName(name);
      when(bedService.updateType(id,bedType.getName())).thenReturn(bedTypeDto);
      final ResponseEntity<BedTypeDto> expected = ResponseEntity.ok(bedTypeDto);
      //WHEN
      final ResponseEntity<BedTypeDto> actual = new BedResource(bedService).updateBedType(id,bedType);

      //THEN
      assertEquals(expected,actual);
    }

  }

}