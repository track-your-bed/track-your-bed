package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.WardTypeService;
import de.wirvsvirus.trackyourbed.dto.response.WardTypeDto;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@DisplayName("Tests for WardTypeResource.")
class WardTypeResourceTest {

  @Nested
  @DisplayName("Test calls to getAllWardTypes.")
  class GetAllWardTypesTest {

    @Test
    @DisplayName("Should return a response entity containing all ward types with status set to " +
        "ok when called.")
    void shouldReturnResponseEntityWithAllWardTypesInBodyAndStatusSetToOkWhenCalled () {
      //GIVEN
      // TODO: refactor to chaining setters after merge
      final WardTypeDto wardOne = new WardTypeDto();
      wardOne.setName("one");
      final WardTypeDto wardTwo = new WardTypeDto();
      wardOne.setName("two");
      final Collection<WardTypeDto> allWardTypes = List.of(wardOne, wardTwo);
      final WardTypeService wardTypeService = mock(WardTypeService.class);
      when(wardTypeService.getAllWardTypes()).thenReturn(allWardTypes);

      //WHEN
      final ResponseEntity<Collection<WardTypeDto>> actual
          = new WardTypeResource(wardTypeService).getAllWardTypes();

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(allWardTypes, actual.getBody());
    }

  }

  @Nested
  @DisplayName("Test calls to getWardTypeByName.")
  class GetWardTypeByNameTest {

    @Test
    @DisplayName("Should return response entity with the corresponding WardTypeDto and Status " +
        "set to Ok when called with name.")
    void shouldReturnResponseEntityWithCorrectWardTypeAndStatusOkWhenCalledWithName () {
      //GIVEN
      final String name = "name";
      // TODO: refactor to chaining setters after merge
      final WardTypeDto wardTypeDto = new WardTypeDto();
      wardTypeDto.setName(name);
      final WardTypeService wardTypeService = mock(WardTypeService.class);
      when(wardTypeService.getWardTypeByName(anyString())).thenReturn(wardTypeDto);

      //WHEN
      final ResponseEntity<WardTypeDto> actual =
          new WardTypeResource(wardTypeService).getWardTypeByName(name);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(wardTypeDto, actual.getBody());

      verify(wardTypeService).getWardTypeByName(eq(name));
    }

  }

}