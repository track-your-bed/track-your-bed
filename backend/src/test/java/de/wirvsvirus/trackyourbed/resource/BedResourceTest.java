package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.BedService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewBed;
import de.wirvsvirus.trackyourbed.dto.response.BedDto;
import java.util.Collection;
import java.util.List;
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
      final BedService bedService = mock(BedService.class);
      final CreateNewBed createNewBed = mock(CreateNewBed.class);
      //TO BE COMPLETED!!!!!!!


    }



  }

  @Nested
  @DisplayName("Test calls to getAllBeds")
  class GetAllBedsTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing a collection of all beds" +
        "with status set to ok")
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

      assertEquals(actual,expected);

    }
  }

  @Nested
  @DisplayName("Test calls to getBedById")
  class getBedByIdTest {
    final BedService bedService = mock(BedService.class);

  }

  @Nested
  @DisplayName("Test calls to patchBed")
  class PatchBedTest {
    final BedService bedService = mock(BedService.class);

  }

  @Nested
  @DisplayName("Test calls to deleteBed")
  class DeleteBedTest {
    final BedService bedService = mock(BedService.class);

  }

  @Nested
  @DisplayName("Test calls to updateBedState")
  class UpdateBedStateTest {
    final BedService bedService = mock(BedService.class);
  }

  @Nested
  @DisplayName("Test calls to updateBedType")
  class UpdateBedType {
    final BedService bedService = mock(BedService.class);

  }

}