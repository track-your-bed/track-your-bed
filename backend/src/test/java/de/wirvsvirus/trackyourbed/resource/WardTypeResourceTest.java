package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.WardTypeService;
import de.wirvsvirus.trackyourbed.dto.response.WardTypeDto;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

@DisplayName("Tests for WardTypeResource")
class WardTypeResourceTest {

  @Nested
  @DisplayName("Test calls to getAllWardTypes")
  class GetAllWardTypesTest {

    @Test
    @DisplayName("Should return a response entity containing " +
        "all ward types with status set to ok when called")
    void shouldReturnResponseEntityWithAllWardTypesInBodyAndStatusSetToOkWhenCalled () {

      //GIVEN
      final WardTypeService wardTypeService = mock(WardTypeService.class);

      final WardTypeDto wardOne = new WardTypeDto();
      final WardTypeDto wardTwo = new WardTypeDto();
      final String wardNameOne = "one";
      final String wardNameTwo = "two";

      wardOne.setName(wardNameOne);
      wardOne.setName(wardNameTwo);

      final Collection<WardTypeDto> allWardTypes = List.of(
          wardOne,
          wardTwo
      );
      when(wardTypeService.getAllWardTypes()).thenReturn(allWardTypes);

      final ResponseEntity<Collection<WardTypeDto>> expected = ResponseEntity.ok(allWardTypes);

      //WHEN
      final ResponseEntity<Collection<WardTypeDto>> actual = new WardTypeResource(wardTypeService).getAllWardTypes();

      //THEN

      assertEquals(actual,expected);
    }
  }

  @Nested
  @DisplayName("Test calls to getWardTypeByName")
  class GetWardTypeByNameTest {



    @Test
    @DisplayName("Should return response entity with the corresponding WardTypeDto" +
        "and Status set to OK When Called with name")
    void shouldReturnResponseEntityWithCorrectWardTypeAndStatusOkWhenCalledWithName () {
      //GIVEN
      final String name = "name";
      final WardTypeService wardTypeService = mock(WardTypeService.class);
      final WardTypeDto wardTypeDto = new WardTypeDto();
      wardTypeDto.setName(name);
      when(wardTypeService.getWardTypeByName(name)).thenReturn(wardTypeDto);

      final ResponseEntity<WardTypeDto> expected = ResponseEntity.ok(wardTypeDto);

      //WHEN
      final ResponseEntity<WardTypeDto> actual = new WardTypeResource(wardTypeService).getWardTypeByName(name);

      //THEN

      assertEquals(actual,expected);

    }

  }


}