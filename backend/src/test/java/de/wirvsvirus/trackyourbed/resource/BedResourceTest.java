package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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
import org.springframework.http.ResponseEntity;

@DisplayName("Tests for BedResource")
class BedResourceTest {

  @Nested
  @DisplayName("Test calls to bedTest")
  class CreateBedTest {

    @Test
    @DisplayName("Should return a response entity containing the created link and bedDto" +
        "when called with createNewbed")
    void ShouldReturnResponseEntityWithCreatedLinkAndBedDtoWhenCalledWithCreateNewBed() {
      //GIVEN
      final BedService bedService = mock(BedService.class);
      final CreateNewBed createNewBed = mock(CreateNewBed.class);
      final String name = "name";
      final String createdLink = "/api/beds/name";
      final BedDto bedDto = new BedDto();
      bedDto.setName(name);
      when(bedService.createNewBed(createNewBed)).thenReturn(bedDto);
      final ResponseEntity<BedDto> expected = ResponseEntity
          .created(URI.create(createdLink))
          .body(bedDto);

      //WHEN
      final ResponseEntity<BedDto> actual = new BedResource(bedService).createBed(createNewBed);

      //THEN
      assertEquals(expected,actual);



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
      final BedService bedService = mock(BedService.class);
      final BedDto bedOne = mock(BedDto.class);
      final BedDto bedTwo = mock(BedDto.class);
      final Collection<BedDto> allBeds = List.of(
          bedOne,
          bedTwo
      );
      final ResponseEntity<Collection<BedDto>> expected = ResponseEntity.ok(allBeds);
      when(bedService.getAllBeds()).thenReturn(allBeds);

      //WHEN
      final ResponseEntity<Collection<BedDto>> actual = new BedResource(bedService).getAllBeds();
      //THEN
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
      final BedService bedService = mock(BedService.class);
      final BedDto bedDto = new BedDto();
      final UUID id = UUID.randomUUID();
      bedDto.setId(id);
      when(bedService.getBedById(id)).thenReturn(bedDto); //any and optional.of missing
      final ResponseEntity<BedDto> expected = ResponseEntity.ok(bedDto);

      //WHEN
      final ResponseEntity<BedDto> actual = new BedResource(bedService).getBedById(id);
      //THEN
      assertEquals(expected, actual);
      //verify....


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
      final BedService bedService = mock(BedService.class);
      final BedDto bedDto = new BedDto();
      final UpdateBed updateBed = new UpdateBed();
      final UUID id = UUID.randomUUID();
      when(bedService.updateBed(id, updateBed)).thenReturn(bedDto);
      final ResponseEntity<BedDto> expected = ResponseEntity.ok(bedDto);
      //WHEN
      final ResponseEntity<BedDto> actual = new BedResource(bedService).patchBed(id, updateBed);
      //THEN
      assertEquals(expected, actual);
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
      final BedService bedService = mock(BedService.class);
      final UUID id = UUID.randomUUID();
      final ResponseEntity<Void> expected = ResponseEntity.ok().build();
      //WHEN
      final ResponseEntity<Void> actual = new BedResource(bedService).deleteBed(id);
      //THEN
      assertEquals(expected,actual);

    }

  }

  @Nested
  @DisplayName("Test calls to updateBedState")
  class UpdateBedStateTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing the updated BedStateDto and Status set to OK" +
        "when called with id and updateBedState")
    void shouldReturnResponseEntityWithUpdatedBedStateDtoandStatusOkWhenCalledWithIdAndUpdateBedState (){
      //GiVEN
      final BedService bedService = mock(BedService.class);
      final UUID id = UUID.randomUUID();
      final String name = "name";
      final BedStateDto bedState= new BedStateDto();
      bedState.setName(name);
      final BedStateDto bedStateDto = new BedStateDto();
      bedStateDto.setName(name);
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