package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.DepartmentTypeService;
import de.wirvsvirus.trackyourbed.dto.response.DepartmentTypeDto;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@DisplayName("Tests DepartmentTypeResource.")
class DepartmentTypeResourceTest {

  @Nested
  @DisplayName("Test Calls to getAllDepartmentTypes.")
  class GetAllDepartmentTypesTest {

    @Test
    @DisplayName("Should return a response entity containing all department types with status set to" +
        " ok when called")
    void shouldReturnResponseEntityWithAllDepartmentTypesInBodyAndStatusSetToOkWhenCalled (){
      // GIVEN
      final DepartmentTypeDto departmentTypeOne = new DepartmentTypeDto();
      final DepartmentTypeDto departmentTypeTwo = new DepartmentTypeDto();
      final String departmentNameOne = "one";
      final String departmentNameTwo = "two";
      departmentTypeOne.setName(departmentNameOne);
      departmentTypeTwo.setName(departmentNameTwo);
      final Collection<DepartmentTypeDto> allDepartmentTypes = List.of(
          departmentTypeOne,
          departmentTypeTwo
      );
      final DepartmentTypeService departmentTypeService = mock(DepartmentTypeService.class);
      when(departmentTypeService.getAllDepartmentTypes()).thenReturn(allDepartmentTypes);

      // WHEN
      final ResponseEntity<Collection<DepartmentTypeDto>> actual =
          new DepartmentTypeResource(departmentTypeService).getAllDepartmentTypes();

      // THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(allDepartmentTypes, actual.getBody());
    }

  }

  @Nested
  @DisplayName("Test calls to getDepartmentTypeByName.")
  class getDepartmentTypeByNameTest {

    @Test
    @DisplayName("Should return response entity containing the corresponding DepartmentTypeDto and" +
        " status set to Ok when called with name.")
    void shouldReturnResponseEntityWithCorrectDepartmentTypeAndStatusOkWhenCalledWithName (){
      //GIVEN
      final String name = "name";
      final DepartmentTypeDto departmentTypeDto = new DepartmentTypeDto();
      departmentTypeDto.setName(name);
      final DepartmentTypeService departmentTypeService = mock(DepartmentTypeService.class);
      when(departmentTypeService.getDepartmentTypeByName(anyString())).thenReturn(departmentTypeDto);

      //WHEN
      final ResponseEntity<DepartmentTypeDto> actual =
          new DepartmentTypeResource(departmentTypeService).getDepartmentTypeByName(name);

      //THEN
      assertEquals(HttpStatus.OK, actual.getStatusCode());
      assertSame(departmentTypeDto, actual.getBody());

      verify(departmentTypeService).getDepartmentTypeByName(eq(name));
    }

  }

}