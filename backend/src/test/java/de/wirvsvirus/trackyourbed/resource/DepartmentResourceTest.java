package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.BedService;
import de.wirvsvirus.trackyourbed.DepartmentService;
import de.wirvsvirus.trackyourbed.dto.response.BedDto;
import de.wirvsvirus.trackyourbed.dto.response.DepartmentDto;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.springframework.http.ResponseEntity;

@DisplayName("Tests for DepartmentResource")
class DepartmentResourceTest {

  @Nested
  @DisplayName("Test calls to createDepartment")
  class CreateDepartmentTest {
    final DepartmentService departmentService = mock(DepartmentService.class);

    @Test
    void Something(){

    }
  }

  @Nested
  @DisplayName("Test calls to getAllDepartments")
  class GetAllDepartmentsTest{
    @Test
    @DisplayName("Should return a ResponseEntity containing a collection of all departments" +
        "with status set to ok when called")
    void shouldReturnResponseEntityContainingCollectionOfAllDepartmentsWithStatusOkWhenCalled() {
      //GIVEN
      final DepartmentService departmentService = mock(DepartmentService.class);
      final DepartmentDto DepartmentOne = mock(DepartmentDto.class);
      final DepartmentDto DepartmentTwo = mock(DepartmentDto.class);
      final Collection<DepartmentDto> allDepartments = List.of(
          DepartmentOne,
          DepartmentTwo
      );
      final ResponseEntity<Collection<DepartmentDto>> expected = ResponseEntity.ok(allDepartments);
      when(departmentService.getAllDepartments()).thenReturn(allDepartments);

      //WHEN
      final ResponseEntity<Collection<DepartmentDto>> actual = new DepartmentResource(departmentService).getAllDepartments();
      //THEN
      assertEquals(actual, expected);

    }

  }

 @Nested
 @DisplayName("Test calls to getDepartmentById")
 class GetDepartmentByIdTest {
   @Test
   @DisplayName("Should return a ResponseEntity containing the correct DepartmentDto" +
       "with Status set to OK when called with  UUID id")
   void shouldReturnResponseEntityWithDepartmentDtoAndStatusOkWhenCalledWithId() {
     //GIVEN
     final DepartmentService departmentService = mock(DepartmentService.class);
     final DepartmentDto departmentDto = new DepartmentDto();
     final UUID id = UUID.randomUUID();
     departmentDto.setId(id);
     when(departmentService.getDepartmentById(id)).thenReturn(departmentDto); //any
     final ResponseEntity<DepartmentDto> expected = ResponseEntity.ok(departmentDto);

     //WHEN
     final ResponseEntity<DepartmentDto> actual = new DepartmentResource(departmentService).getDepartmentById(id);
     //THEN
     assertEquals(expected, actual);
     //verify....


   }

 }

 @Nested
 @DisplayName("Test calls to patchDepartment")
 class PatchDepartmentTest {

 }

  @Nested
  @DisplayName("Test calls to deleteDepartment")
  class DeleteDepartmentTest {

  }

  @Nested
  @DisplayName("Test calls to getCapacity")
  class GetCapacityTest{

  }

}