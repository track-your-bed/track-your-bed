package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.DepartmentTypeService;
import de.wirvsvirus.trackyourbed.dto.response.DepartmentTypeDto;
import de.wirvsvirus.trackyourbed.entity.DepartmentType;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

@DisplayName("Tests DepartmentTypeResource")
class DepartmentTypeResourceTest {

  @Nested
  @DisplayName("Test Calls to getAllDepartmentTypes")
  class GetAllDepartmentTypes {

    @Test
    @DisplayName("Should return a response entity containing " +
        "all department types with status set to ok when called")
    void shouldReturnResponseEntityWhenCalledWithAllDepartmentTypesInBodyAndStatusSetToOkWhenCalled (){

      // GIVEN

      final DepartmentTypeService departmentTypeService = mock(DepartmentTypeService.class);

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
      when(departmentTypeService.getAllDepartmentTypes()).thenReturn(allDepartmentTypes);
      final ResponseEntity<Collection<DepartmentTypeDto>> expected = ResponseEntity.ok(allDepartmentTypes);

      // WHEN
      final ResponseEntity<Collection<DepartmentTypeDto>> actual = new DepartmentTypeResource(departmentTypeService).getAllDepartmentTypes();

      // THEN
      assertEquals(actual,expected);

    }
  }

  @Nested
  @DisplayName("Test calls to getDepartmentTypeByName")
  class getDepartmentTypeByNameTest {

    @Test
    @DisplayName("Should return response entity with the corresponding DepartmentTypeDto" +
        "and Status set to OK When Called with name")
    void shouldReturnResponseEntityWithCorrectDepartmentTypeAndStatusOkWhenCalledWithName (){

      //GIVEN
      final DepartmentTypeService departmentTypeService = mock(DepartmentTypeService.class);
      final String name = "name";
      final DepartmentTypeDto departmentTypeDto = new DepartmentTypeDto();
      departmentTypeDto.setName(name);
      when(departmentTypeService.getDepartmentTypeByName(name)).thenReturn(departmentTypeDto);

      final ResponseEntity<DepartmentTypeDto> expected = ResponseEntity.ok(departmentTypeDto);

      //WHEN
      final ResponseEntity<DepartmentTypeDto> actual = new DepartmentTypeResource(departmentTypeService).getDepartmentTypeByName(name);

      //THEN

      assertEquals(actual,expected);

    }
  }

}