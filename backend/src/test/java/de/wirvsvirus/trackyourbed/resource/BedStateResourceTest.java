package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.BedStateService;
import de.wirvsvirus.trackyourbed.dto.response.BedStateDto;
import java.util.Collection;
import java.util.Optional;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;


@DisplayName("Tests for BedStateResource.")
class BedStateResourceTest {

  @Nested
  @DisplayName("Test calls to getAllBedStates.")
  class getAllBedStatesTest {

    @Test
    @DisplayName("Should return the response entity with Status set to OK and body containing " +
        "all bed states.")
    void shouldReturnResponseEntityWithAllBedStatesAndStatusSetToOKWhenCalled() {
      // GIVEN
      final String bedStateOneName = "one";
      final String bedStateTwoName = "two";
      final Collection<BedStateDto> allBedStates = List.of(
          new BedStateDto().setName(bedStateOneName),
          new BedStateDto().setName(bedStateTwoName)
      );
      final BedStateService bedStateService = mock(BedStateService.class);
      when(bedStateService.getAllBedStates()).thenReturn(allBedStates);

      final ResponseEntity<Collection<BedStateDto>> expected = ResponseEntity.ok(allBedStates);

      // WHEN
      final ResponseEntity<Collection<BedStateDto>> actual =
          new BedStateResource(bedStateService).getAllBedStates();

      // THEN
      assertEquals(expected, actual);
    }

  }

  @Nested
  @DisplayName("Test calls to getBedStateByName")
  class getBedStateByNameTest {

    @Test
    @DisplayName("To BE COMPLETED")
    void shouldReturnResponseEntityWithCorrectBedStatesAndStatusSetToOKWhenCalled(){
      // GIVEN
      final String name = "name";
      final BedStateDto bedStateDto = new BedStateDto().setName(name);
      final BedStateService bedStateService = mock(BedStateService.class);
      when(bedStateService.getBedStateByName(anyString())).thenReturn(bedStateDto);

      final ResponseEntity<BedStateDto> expected = ResponseEntity.ok(bedStateDto);

      // WHEN
      final ResponseEntity<BedStateDto> actual =
          new BedStateResource(bedStateService).getBedStateByName(name);

      //THEN
      assertEquals(actual,expected);

      verify(bedStateService).getBedStateByName(eq(name));
    }

  }

}