package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


import de.wirvsvirus.trackyourbed.BedService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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

    }



  }

  @Nested
  @DisplayName("Test calls to getAllBeds")
  class GetAllBedsTest {
    final BedService bedService = mock(BedService.class);

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