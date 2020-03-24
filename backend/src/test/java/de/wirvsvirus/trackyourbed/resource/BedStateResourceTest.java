package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import org.springframework.http.HttpStatus;
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

      // WHEN
      final ResponseEntity<Collection<BedStateDto>> actual =
          new BedStateResource(bedStateService).getAllBedStates();

      // THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(allBedStates, actual.getBody());
    }

  }

  @Nested
  @DisplayName("Test calls to getBedStateByName.")
  class getBedStateByNameTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing the bedStateDto with Status set to OK" +
        "when called.")
    void shouldReturnResponseEntityWithCorrectBedStatesAndStatusSetToOkWhenCalled(){
      // GIVEN
      final String name = "name";
      final BedStateDto bedStateDto = new BedStateDto().setName(name);
      final BedStateService bedStateService = mock(BedStateService.class);
      when(bedStateService.getBedStateByName(anyString())).thenReturn(bedStateDto);

      // WHEN
      final ResponseEntity<BedStateDto> actual =
          new BedStateResource(bedStateService).getBedStateByName(name);

      //THEN
      assertEquals(HttpStatus.OK,actual.getStatusCode());
      assertSame(bedStateDto, actual.getBody());

      verify(bedStateService).getBedStateByName(eq(name));
    }

  }

}