package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.BedStateService;
import de.wirvsvirus.trackyourbed.BedTypeService;
import de.wirvsvirus.trackyourbed.dto.response.BedTypeDto;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@DisplayName("Tests for BedTypeResourceTest.")
class BedTypeResourceTest {

  @Nested
  @DisplayName("Test calls to getAllBedTypes.")
  class GetAllBedTypes {

    @Test
    @DisplayName("Should return a response entity containing all bed types in its body with "
        + "status set to ok.")
    void shouldReturnResponseEntityWithAllBedTypesInBodyAndStatusSetToOkWhenCalled() {
      //GIVEN
      final String bedTypeOneName = "one";
      final String bedTypeTwoName = "two";
      // TODO: refactor to chaining setters after merge
      final BedTypeDto bedOne = new BedTypeDto();
      bedOne.setName(bedTypeOneName);
      final BedTypeDto bedTwo = new BedTypeDto();
      bedTwo.setName(bedTypeTwoName);
      final Collection<BedTypeDto> allBedTypes = List.of(bedOne, bedTwo);
      final BedTypeService bedStateService = mock(BedTypeService.class);
      when(bedStateService.getAllBedTypes()).thenReturn(allBedTypes);

      //WHEN
      final ResponseEntity<Collection<BedTypeDto>> actual =
          new BedTypeResource(bedStateService).getAllBedTypes();

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(allBedTypes, actual.getBody());
    }

  }

  @Nested
  @DisplayName("Test calls to getBedTypeByName.")
  class GetBedTypeNameTest {

    @Test
    @DisplayName("Should return a response entity containing the correct name with status set to " +
        "ok.")
    void shouldReturnResponseEntityWithCorrectBedTypeAndStatusSetToOkWhenCalledWithName (){
      //GIVEN
      final String name = "name";
      // TODO: refactor to chaining setters after merge
      final BedTypeDto bedTypeDto = new BedTypeDto();
      bedTypeDto.setName(name);
      final BedTypeService bedTypeService = mock(BedTypeService.class);
      when(bedTypeService.getBedTypeByName(anyString())).thenReturn(bedTypeDto);

      //WHEN
      final ResponseEntity<BedTypeDto> actual =
          new BedTypeResource(bedTypeService).getBedTypeByName(name);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(bedTypeDto, actual.getBody());

      verify(bedTypeService).getBedTypeByName(eq(name));
    }

  }

}