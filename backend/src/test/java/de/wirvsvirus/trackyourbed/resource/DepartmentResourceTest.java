package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.DepartmentService;
import de.wirvsvirus.trackyourbed.dto.request.CreateNewDepartment;
import de.wirvsvirus.trackyourbed.dto.request.UpdateDepartment;
import de.wirvsvirus.trackyourbed.dto.response.DepartmentCapacityDto;
import de.wirvsvirus.trackyourbed.dto.response.DepartmentDto;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@DisplayName("Tests for DepartmentResource.")
class DepartmentResourceTest {

  @Nested
  @DisplayName("Test calls to createDepartment.")
  class CreateDepartmentTest {

    @Test
    @DisplayName("Should return a response entity containing the created link and departmentDto when" +
        " called with createNewDepartment.")
    void ShouldReturnResponseEntityWithCreatedLinkAndDepartmentDtoWhenCalledWithCreateNewDepartment() {
      //GIVEN
      final CreateNewDepartment createNewDepartment = mock(CreateNewDepartment.class);
      final UUID id = UUID.randomUUID();
      final DepartmentDto departmentDto = new DepartmentDto();
      departmentDto.setId(id);
      final DepartmentService departmentService = mock(DepartmentService.class);
      when(departmentService.createDepartment(any(CreateNewDepartment.class))).thenReturn(departmentDto);

      final List<String> expectedLink = List.of(String.format("/api/departments/%s",id));

      //WHEN
      final ResponseEntity<DepartmentDto> actual =
          new DepartmentResource(departmentService).createDepartment(createNewDepartment);

      //THEN
      assertEquals(HttpStatus.CREATED, actual.getStatusCode());
      assertEquals(expectedLink, actual.getHeaders().get("Location"));
      assertSame(departmentDto, actual.getBody());

      verify(departmentService).createDepartment(eq(createNewDepartment));
    }

  }

  @Nested
  @DisplayName("Test calls to getAllDepartments.")
  class GetAllDepartmentsTest{
    @Test
    @DisplayName("Should return a ResponseEntity containing a collection of all departments with " +
        "status set to Ok when called.")
    void shouldReturnResponseEntityContainingCollectionOfAllDepartmentsWithStatusOkWhenCalled() {
      //GIVEN
      final DepartmentDto departmentOne = mock(DepartmentDto.class);
      final DepartmentDto departmentTwo = mock(DepartmentDto.class);
      final Collection<DepartmentDto> allDepartments = List.of(
          departmentOne,
          departmentTwo
      );
      final DepartmentService departmentService = mock(DepartmentService.class);
      when(departmentService.getAllDepartments()).thenReturn(allDepartments);

      //WHEN
      final ResponseEntity<Collection<DepartmentDto>> actual =
          new DepartmentResource(departmentService).getAllDepartments();

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(allDepartments, actual.getBody());
    }

  }

 @Nested
 @DisplayName("Test calls to getDepartmentById.")
 class GetDepartmentByIdTest {

   @Test
   @DisplayName("Should return a ResponseEntity containing the correct DepartmentDto with Status set" +
       " to OK when called with  UUID id")
   void shouldReturnResponseEntityWithDepartmentDtoAndStatusOkWhenCalledWithId() {
     //GIVEN
     final DepartmentDto departmentDto = new DepartmentDto();
     final UUID id = UUID.randomUUID();
     departmentDto.setId(id);
     final DepartmentService departmentService = mock(DepartmentService.class);
     when(departmentService.getDepartmentById(any(UUID.class))).thenReturn(departmentDto);

     //WHEN
     final ResponseEntity<DepartmentDto> actual =
         new DepartmentResource(departmentService).getDepartmentById(id);

     //THEN
     assertEquals(HttpStatus.OK, actual.getStatusCode());
     assertSame(departmentDto, actual.getBody());

     verify(departmentService).getDepartmentById(eq(id));
   }

 }

 @Nested
 @DisplayName("Test calls to patchDepartment.")
 class PatchDepartmentTest {

   @Test
   @DisplayName("Should return a ResponseEntity containing the updated departmentDto with Status set" +
       " to OK when called with a UUID and an UpdateDepartment")
   void shouldReturnResponseEntityContainingDepartmentDtoWithStatusOkWhenCalledWithUuidAndUpdateDepartment() {
     //GIVEN
     final DepartmentDto departmentDto = new DepartmentDto();
     final UpdateDepartment updateDepartment = new UpdateDepartment();
     final UUID id = UUID.randomUUID();
     final DepartmentService departmentService = mock(DepartmentService.class);
     when(departmentService.updateDepartment(any(UUID.class), any(UpdateDepartment.class))).
         thenReturn(departmentDto);

     //WHEN
     final ResponseEntity<DepartmentDto> actual =
         new DepartmentResource(departmentService).patchDepartment(id, updateDepartment);

     //THEN
     assertEquals(HttpStatus.OK, actual.getStatusCode());
     assertSame(departmentDto, actual.getBody());

     verify(departmentService).updateDepartment(eq(id), eq(updateDepartment));
   }

 }

  @Nested
  @DisplayName("Test calls to deleteDepartment.")
  class DeleteDepartmentTest {

    @Test
    @DisplayName("Should return a ResponseEntity with empty body and status set to OK when called " +
        "with Id.")
    void shouldReturnEmptyResponseEntityWithStatusOkWhenCalledWithId (){
      //GIVEN
      final DepartmentService departmentService = mock(DepartmentService.class);
      final UUID id = UUID.randomUUID();
      final ResponseEntity<Void> expected = ResponseEntity.ok().build();

      //WHEN
      final ResponseEntity<Void> actual =
          new DepartmentResource(departmentService).deleteDepartment(id);
      //THEN
      assertEquals(expected, actual);
      assertEquals(HttpStatus.OK, actual.getStatusCode());

      verify(departmentService).deleteDepartmentById(eq(id));
    }

  }

  @Nested
  @DisplayName("Test calls to getCapacity.")
  class GetCapacityTest {

    @Test
    @DisplayName("Should return a ResponseEntity containing a DepartmentCapacityDto with Status OK" +
        "when called with an Id.")
    void shouldReturnResponseEntityContainingDepartmentCapacityDtoWithStatusOkWhenCalledWithId(){
      //GIVEN
      final UUID id = UUID.randomUUID();
      final DepartmentService departmentService = mock(DepartmentService.class);
      final DepartmentCapacityDto departmentCapacityDto = mock(DepartmentCapacityDto.class);
      when(departmentService.calculateCapacity(any(UUID.class))).thenReturn(departmentCapacityDto);

      //WHEN
      final ResponseEntity<DepartmentCapacityDto> actual =
          new DepartmentResource(departmentService).getCapacity(id);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(departmentCapacityDto, actual.getBody());

      verify(departmentService).calculateCapacity(eq(id));
    }

  }

}