package de.wirvsvirus.trackyourbed;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import de.wirvsvirus.trackyourbed.dto.request.CreateNewWard;
import de.wirvsvirus.trackyourbed.dto.request.mapper.CreateNewWardMapper;
import de.wirvsvirus.trackyourbed.dto.response.WardDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.FlatCapacityDtoMapper;
import de.wirvsvirus.trackyourbed.dto.response.mapper.WardDtoMapper;
import de.wirvsvirus.trackyourbed.entity.Department;
import de.wirvsvirus.trackyourbed.entity.Ward;
import de.wirvsvirus.trackyourbed.entity.WardType;
import de.wirvsvirus.trackyourbed.excpetion.dependency.DepartmentMissingException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.InvalidWardTypeException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchWardException;
import de.wirvsvirus.trackyourbed.persistence.DepartmentRepository;
import de.wirvsvirus.trackyourbed.persistence.WardRepository;
import de.wirvsvirus.trackyourbed.persistence.WardTypeRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@DisplayName("Test WardService")
class WardServiceTest {
  WardRepository wardRepository;
  DepartmentRepository departmentRepository;
  WardTypeRepository wardTypeRepository;
  CapacityService capacityService;
  CreateNewWardMapper createNewWardMapper;
  WardDtoMapper wardDtoMapper;
  FlatCapacityDtoMapper flatCapacityDtoMapper;
  WardService wardService;

  @BeforeEach
  void initialize() {
    wardRepository = mock(WardRepository.class);
    departmentRepository = mock(DepartmentRepository.class);
    wardTypeRepository = mock(WardTypeRepository.class);
    capacityService = mock(CapacityService.class);
    createNewWardMapper = mock(CreateNewWardMapper.class);
    wardDtoMapper = mock(WardDtoMapper.class);
    flatCapacityDtoMapper = mock(FlatCapacityDtoMapper.class);
    wardService = new WardService(wardRepository,
        departmentRepository,
        wardTypeRepository,
        capacityService,
        createNewWardMapper,
        wardDtoMapper,
        flatCapacityDtoMapper);
  }

  @Nested
  @DisplayName("Test calls to getWardByName")
  class GetWardByNameTest {


    @Test
    void shouldCallRepositoryAndReturnExpectedResultWhenCalled() {
      // GIVEN
      final UUID wardId = UUID.randomUUID();

      final Ward ward = new Ward();
      final WardDto expected = new WardDto();
      when(wardRepository.findById(any(UUID.class))).thenReturn(Optional.of(ward));
      when(wardDtoMapper.entityToDto(ward)).thenReturn(expected);

      // WHEN
      final WardDto actual = wardService.getWardById(wardId);

      // THEN
      assertSame(expected, actual);

      verify(wardRepository).findById(eq(wardId));
      verify(wardDtoMapper).entityToDto(refEq(ward));
    }

    @Test
    void shouldThrowNoSuchBedExceptionWhenWardDoesNotExist() {
      // GIVEN
      final UUID wardId = UUID.randomUUID();
      final WardRepository wardRepository = mock(WardRepository.class);
      when(wardRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
      final String expectedMessage = format(NoSuchWardException.MESSAGE_TEMPLATE, wardId);

      // WHEN
      final NoSuchWardException e = assertThrows(NoSuchWardException.class,
          () -> wardService.getWardById(wardId));

      // THEN
      assertNotNull(e);
      assertEquals(expectedMessage, e.getMessage());
    }
  }

  @Nested
  @DisplayName("Test Calls to createNewWard")
  class CreateNewWardTest {
    @Test
    void shouldAddWardToRepositoryAndReturnSavedEntity() {
      //GIVEN
      final CreateNewWard request = new CreateNewWard();
      final UUID departmentId = UUID.randomUUID();
      final String wardTypeName = "somename";
      final Department department = new Department();
      final WardType wardType = new WardType();
      final Ward wardFromChangeRequest = mock(Ward.class);
      final Ward persistedWard = mock(Ward.class);
      final WardDto expected = new WardDto();

      request.setDepartmentId(departmentId);
      request.setWardType(wardTypeName);

      when(createNewWardMapper.dtoToEntity(request)).thenReturn(wardFromChangeRequest);
      when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
      when(wardTypeRepository.findByName(wardTypeName)).thenReturn(Optional.of(wardType));
      when(wardRepository.save(refEq(wardFromChangeRequest))).thenReturn(persistedWard);
      when(wardDtoMapper.entityToDto(persistedWard)).thenReturn(expected);

      //WHEN
      final WardDto actual = wardService.createNewWard(request);

      //THEN
      assertEquals(expected, actual);
      verify(createNewWardMapper).dtoToEntity(request);
      verify(departmentRepository).findById(departmentId);
      verify(wardTypeRepository).findByName(wardTypeName);
      verify(wardFromChangeRequest).setDepartment(department);
      verify(wardFromChangeRequest).setWardType(wardType);
      verify(wardRepository).save(wardFromChangeRequest);
      verify(wardDtoMapper).entityToDto(persistedWard);

    }

    @Test
    void shouldThrowInvalidWardTypeExceptionOnWardTypeNotFoundInRepository() {
      //GIVEN
      final CreateNewWard request = new CreateNewWard();
      final String wardTypeName = "somename";
      request.setWardType(wardTypeName);
      when(departmentRepository.findById(any())).thenReturn(Optional.of(new Department()));
      when(wardTypeRepository.findByName(wardTypeName)).thenReturn(Optional.empty());
      final String expectedMessage = format(InvalidWardTypeException.MESSAGE_TEMPLATE, wardTypeName);

      //WHEN
      final InvalidWardTypeException actual = assertThrows(InvalidWardTypeException.class,
          () -> wardService.createNewWard(request)
      );

      //THEN
      assertNotNull(actual);
      assertEquals(expectedMessage, actual.getMessage());

    }

    @Test
    void shouldThrowDepartmantMissingExceptionOnWDepartmentNotFoundInRepository() {
      //GIVEN
      final CreateNewWard request = new CreateNewWard();
      final UUID departmentId = UUID.randomUUID();
      request.setDepartmentId(departmentId);
      when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());
      when(wardTypeRepository.findByName(any())).thenReturn(Optional.of(new WardType()));
      final String expectedMessage = format(DepartmentMissingException.MESSAGE_TEMPLATE, departmentId);

      //WHEN
      final DepartmentMissingException actual = assertThrows(DepartmentMissingException.class,
          () -> wardService.createNewWard(request)
      );

      //THEN
      assertNotNull(actual);
      assertEquals(expectedMessage, actual.getMessage());

    }

  }



  @Nested
  @DisplayName("Test calls to getAllWards")
  class GetAllWardsTest {
    @Test
    void shouldFetchAllWardsFromRepository() {
      // GIVEN
      final List<Ward> wards = new ArrayList<>();
      final List<WardDto> expected = new ArrayList<>();

      for (int i = 0 ; i < 2 ; ++i) {
        final Ward ward = new Ward();
        ward.setId(UUID.randomUUID());
        final WardDto wardDto = new WardDto();
        when(wardDtoMapper.entityToDto(ward)).thenReturn(wardDto);
        wards.add(ward);
        expected.add(wardDto);
      }

      when(wardRepository.findAll()).thenReturn(wards);

      //WHEN
      final Collection<WardDto> actual = wardService.getAllWards();

      //THEN
      assertThat(actual, Matchers.containsInAnyOrder(expected.toArray()));
      verify(wardRepository).findAll();
      for (final Ward ward : wards) {
        verify(wardDtoMapper).entityToDto(refEq(ward));
      }

    }

    @Test
    void shouldNotFailOnEmptyRepository() {
      // GIVEN
      when(wardRepository.findAll()).thenReturn(new ArrayList<>());

      //WHEN
      final Collection<WardDto> actual = wardService.getAllWards();

      //THEN
      assertThat(actual, Matchers.empty());
      verify(wardRepository).findAll();

    }

  }

}
