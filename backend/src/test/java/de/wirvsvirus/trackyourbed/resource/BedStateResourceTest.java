package de.wirvsvirus.trackyourbed.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.wirvsvirus.trackyourbed.BedStateService;
import de.wirvsvirus.trackyourbed.entity.BedState;
import de.wirvsvirus.trackyourbed.dto.response.BedStateDto;
import java.util.Collection;
import java.util.List;
import org.springframework.http.ResponseEntity;


@DisplayName("Tests for BedStateResource")
class BedStateResourceTest {

  @Nested
  @DisplayName("Test calls to getAllBedStates")
  class getAllBedStatesTest {

    void shouldReturnResponseEntityWithAllBedStatesAndStatusSetToOK() {

      // GIVEN
      final BedStateService bedStateService = mock(BedStateService.class);

      final String bedStateOneName = "one";
      final String bedStateTwoName = "two";
      final Collection<BedStateDto> allBedStates = List.of(
          new BedStateDto().setName(bedStateOneName),
          new BedStateDto().setName(bedStateTwoName)
      );
      when(bedStateService.getAllBedStates()).thenReturn(allBedStates);
      final ResponseEntity<Collection<BedStateDto>> expected = ResponseEntity.ok(allBedStates);


      // WHEN
      final ResponseEntity<Collection<BedStateDto>> actual = new BedStateResource(bedStateService).getAllBedStates();


      // THEN
      assertEquals(actual,expected);

    }



  }


  @Test
  void getBedStateByName() {
  }
}