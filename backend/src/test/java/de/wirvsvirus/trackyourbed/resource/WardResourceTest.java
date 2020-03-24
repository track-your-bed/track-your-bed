package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.DepartmentService;
import de.wirvsvirus.trackyourbed.WardService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewDepartment;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewWard;
import de.wirvsvirus.trackyourbed.dto.request.UpdateDepartment;
import de.wirvsvirus.trackyourbed.dto.request.UpdateWard;
import de.wirvsvirus.trackyourbed.dto.response.DepartmentDto;
import de.wirvsvirus.trackyourbed.dto.response.WardDto;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

@DisplayName("Test for WardResource")
class WardResourceTest {

  @Nested
  @DisplayName("Test calls to createNewWard")
  class CreateNewWardTest {

    @Test
    @DisplayName("Should return a response entity containing the created link and wardDto" +
        "when called with createNewWard")
    void ShouldReturnResponseEntityWithCreatedLinkAndWardDtoWhenCalledWithCreateNewWard() {
      //GIVEN
      final WardService wardService = mock(WardService.class);
      final CreateNewWard createNewWard = mock(CreateNewWard.class);
      final UUID id = UUID.randomUUID();
      final String createdLink = String.format("/api/wards/%s",id);
      final WardDto wardDto = new WardDto();
      wardDto.setId(id);
      when(wardService.createNewWard(createNewWard)).thenReturn(wardDto);
      final ResponseEntity<WardDto> expected = ResponseEntity
          .created(URI.create(createdLink))
          .body(wardDto);

      //WHEN
      final ResponseEntity<WardDto> actual = new WardResource(wardService).createNewWard(createNewWard);

      //THEN
      assertEquals(expected,actual);
    }

  }
  @Nested
  @DisplayName("Test calls to getAllWards")
  class GetAllWardsTest{
    @Test
    @DisplayName("Should return a ResponseEntity containing a collection of all wards" +
        "with status set to ok when called")
    void shouldReturnResponseEntityContainingCollectionOfAllWardsWithStatusOkWhenCalled() {
      //GIVEN
      final WardService wardService = mock(WardService.class);
      final WardDto wardOne = mock(WardDto.class);
      final WardDto wardTwo = mock(WardDto.class);
      final Collection<WardDto> allWards = List.of(
          wardOne,
          wardTwo
      );
      final ResponseEntity<Collection<WardDto>> expected = ResponseEntity.ok(allWards);
      when(wardService.getAllWards()).thenReturn(allWards);

      //WHEN
      final ResponseEntity<Collection<WardDto>> actual = new WardResource(wardService).getAllWards();
      //THEN
      assertEquals(actual, expected);

    }

  }

  @Nested
  @DisplayName("Test calls to getWardById")
  class GetWardByIdTest {
    @Test
    @DisplayName("Should return a ResponseEntity containing the correct WardDto" +
        "with Status set to OK when called with  UUID id")
    void shouldReturnResponseEntityWithWardDtoAndStatusOkWhenCalledWithId() {
      //GIVEN
      final WardService wardService = mock(WardService.class);
      final WardDto wardDto = new WardDto();
      final UUID id = UUID.randomUUID();
      wardDto.setId(id);
      when(wardService.getWardById(id)).thenReturn(wardDto); //any
      final ResponseEntity<WardDto> expected = ResponseEntity.ok(wardDto);

      //WHEN
      final ResponseEntity<WardDto> actual = new WardResource(wardService).getWardById(id);
      //THEN
      assertEquals(expected, actual);
      //verify....


    }

  }

  @Nested
  @DisplayName("Test calls to patchWard")
  class PatchWardTest {
    @Test
    @DisplayName("Should return a ResponseEntity containing the updated wardDto" +
        "with Status set to OK when called with a UUID and an UpdateWard")
    void shouldReturnResponseEntityContainingWardDtoWithStatusOkWhenCalledWithUuidAndUpdateWard() {
      //GIVEN
      final WardService wardService = mock(WardService.class);
      final WardDto wardDto = new WardDto();
      final UpdateWard updateWard = new UpdateWard();
      final UUID id = UUID.randomUUID();
      when(wardService.updateWard(id, updateWard)).thenReturn(wardDto);
      final ResponseEntity<WardDto> expected = ResponseEntity.ok(wardDto);
      //WHEN
      final ResponseEntity<WardDto> actual = new WardResource(wardService).patchWard(id, updateWard);
      //THEN
      assertEquals(expected, actual);
    }

  }

}