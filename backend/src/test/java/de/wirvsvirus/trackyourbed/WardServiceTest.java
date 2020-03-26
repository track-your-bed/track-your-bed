package de.wirvsvirus.trackyourbed;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewWard;
import de.wirvsvirus.trackyourbed.dto.request.UpdateWard;
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
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.verification.VerificationMode;

@DisplayName("Test WardService.")
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
  @DisplayName("Test calls to getWardByID.")
  class GetWardByIdTest {


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
      final String expectedMessage = String.format(NoSuchWardException.MESSAGE_TEMPLATE, wardId);

      // WHEN
      final NoSuchWardException e = assertThrows(
          NoSuchWardException.class,
          () -> wardService.getWardById(wardId));

      // THEN
      assertNotNull(e);
      assertEquals(expectedMessage, e.getMessage());
    }
  }

  @Nested
  @DisplayName("Test Calls to createNewWard.")
  class CreateNewWardTest {
    @Test
    @DisplayName("Should Add ward to repository and return the saved entity.")
    void shouldAddWardToRepositoryAndReturnSavedEntity() {
      //GIVEN
      final UUID departmentId = UUID.randomUUID();
      final String wardTypeName = "wardType";

      // TODO: change to chaining Setter after merge
      final CreateNewWard request = new CreateNewWard();
      request.setDepartmentId(departmentId);
      request.setWardType(wardTypeName);
      final Ward toUpdate = new Ward();
      when(createNewWardMapper.dtoToEntity(any(CreateNewWard.class))).thenReturn(toUpdate);
      final WardType wardType = new WardType();
      when(wardTypeRepository.findByName(anyString())).thenReturn(Optional.of(wardType));

      final Department department = new Department();
      when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));

      final Ward updated = mock(Ward.class);
      when(wardRepository.save(any(Ward.class))).thenReturn(updated);

      final WardDto expected = new WardDto();
      when(wardDtoMapper.entityToDto(any(Ward.class))).thenReturn(expected);

      //WHEN
      final WardDto actual = wardService.createNewWard(request);

      //THEN
      assertEquals(expected, actual);
      verify(createNewWardMapper).dtoToEntity(same(request));
      verify(departmentRepository).findById(eq(departmentId));
      verify(wardTypeRepository).findByName(eq(wardTypeName));
      verify(wardRepository).save(argThat(w -> {
        assertSame(wardType, w.getWardType());
        assertSame(department, w.getDepartment());
        return true;
      }));
      verify(wardDtoMapper).entityToDto(updated);

    }

    @Test
    @DisplayName("Should throw an InvalidWardException when ward type is not found.")
    void shouldThrowInvalidWardTypeExceptionWhenWardTypeNotFound() {
      //GIVEN
      final String wardTypeName = "wardTypeName";
      // TODO: change to chaining Setter after merge
      final CreateNewWard request = new CreateNewWard();
      request.setWardType(wardTypeName);
      request.setDepartmentId(UUID.randomUUID());
      final Department department = new Department();
      when(departmentRepository.findById(any(UUID.class)))
          .thenReturn(Optional.of(department));
      when(wardTypeRepository.findByName(anyString())).thenReturn(Optional.empty());
      final String expectedMessage =
          String.format(InvalidWardTypeException.MESSAGE_TEMPLATE, wardTypeName);

      //WHEN
      final InvalidWardTypeException actual = assertThrows(
          InvalidWardTypeException.class,
          () -> wardService.createNewWard(request)
      );

      //THEN
      assertNotNull(actual);
      assertEquals(expectedMessage, actual.getMessage());

    }

    @Test
    @DisplayName("Should throw a DepartmentMissingException when department is not found.")
    void shouldThrowDepartmentMissingExceptionWhenDepartmentNotFound() {
      //GIVEN
      final UUID departmentId = UUID.randomUUID();
      // TODO: change to chaining Setter after merge
      final CreateNewWard request = new CreateNewWard();
      request.setDepartmentId(departmentId);
      when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
      when(wardTypeRepository.findByName(anyString())).thenReturn(Optional.of(new WardType()));
      final String expectedMessage =
          String.format(DepartmentMissingException.MESSAGE_TEMPLATE, departmentId);

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
  @DisplayName("Test Calls to deleteWardById")
  class DeleteWardByIdTest {
    @Test
    @DisplayName("Shoul call delete on repository")
    void shouldDeleteWardById() {
      //GIVEN
      final UUID wardId = UUID.randomUUID();

      //WHEN
      wardService.deleteWardById(wardId);

      //THEN
      verify(wardRepository).deleteById(wardId);
    }
  }

  @Nested
  @DisplayName("Test calls to updateWard")
  class UpdateWardTest {
    private final UUID wardId = UUID.randomUUID();
    private Ward wardToUpdate;
    private WardDto returnValueFromDtoMapper = new WardDto();

    @BeforeEach
    void initializeMocks() {
      wardToUpdate = mock(Ward.class);
      when(wardRepository.findById(wardId)).thenReturn(Optional.of(wardToUpdate));
      when(wardDtoMapper.entityToDto(any(Ward.class))).thenReturn(returnValueFromDtoMapper);
    }

    @Test
    @DisplayName("Should update ward name if set")
    void shouldUpdateWardNameIfSet() {
      //GIVEN
      final String wardName = "name";
      final UpdateWard updateWardRequest = new UpdateWard().setName(wardName);

      //WHEN
      WardDto actual = wardService.updateWard(wardId, updateWardRequest);

      //THEN
      assertEquals(returnValueFromDtoMapper, actual);
      verify(wardToUpdate, times(1)).setName(wardName);
      verify(wardToUpdate, never()).setWardType(any());
      verify(wardToUpdate, never()).setDepartment(any());
      verify(wardToUpdate, never()).setBeds(any());
      verify(wardToUpdate, never()).setId(any());

    }

    @Test
    @DisplayName("Should update ward type if set")
    void shouldUpdateWardTypeIfSet() {
      //GIVEN
      final String wardTypeName = "name";
      final WardType wardType = new WardType().setName(wardTypeName);
      final UpdateWard updateWardRequest = new UpdateWard().setWardType(wardTypeName);
      when(wardTypeRepository.findByName(wardTypeName)).thenReturn(Optional.of(wardType));

      //WHEN
      WardDto actual = wardService.updateWard(wardId, updateWardRequest);

      //THEN
      assertEquals(returnValueFromDtoMapper, actual);
      verify(wardToUpdate, never()).setName(any());
      verify(wardToUpdate, times(1)).setWardType(wardType);
      verify(wardToUpdate, never()).setDepartment(any());
      verify(wardToUpdate, never()).setBeds(any());
      verify(wardToUpdate, never()).setId(any());

    }

    @Test
    @DisplayName("Should update department if set")
    void shouldUpdateDepartmentIfSet() {
      //GIVEN
      final UUID departmentId = UUID.randomUUID();
      final Department department = new Department().setId(departmentId);
      final UpdateWard updateWardRequest = new UpdateWard().setDepartmentId(departmentId);
      when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));

      //WHEN
      WardDto actual = wardService.updateWard(wardId, updateWardRequest);

      //THEN
      assertEquals(returnValueFromDtoMapper, actual);
      verify(wardToUpdate, never()).setName(any());
      verify(wardToUpdate, never()).setWardType(any());
      verify(wardToUpdate, times(1)).setDepartment(department);
      verify(wardToUpdate, never()).setBeds(any());
      verify(wardToUpdate, never()).setId(any());

    }

  }

  @Nested
  @DisplayName("Test calls to getAllWards.")
  class GetAllWardsTest {
    @Test
    @DisplayName("Should fetch all wards.")
    void shouldFetchAllWards() {
      // GIVEN
      final List<Ward> wards = new ArrayList<>();
      final List<WardDto> expected = new ArrayList<>();

      when(wardRepository.findAll()).thenReturn(wards);
      when(wardDtoMapper.entitiesToDtos(anyList())).thenReturn(expected);

      //WHEN
      final Collection<WardDto> actual = wardService.getAllWards();

      //THEN
      assertEquals(expected, actual);

      verify(wardRepository).findAll();
      verify(wardDtoMapper).entitiesToDtos(same(wards));
    }

    @Test
    @DisplayName("Should not fail on empty repository.")
    void shouldNotFailOnEmptyRepository() {
      // GIVEN
      when(wardRepository.findAll()).thenReturn(new ArrayList<>());

      //WHEN
      final Collection<WardDto> actual = wardService.getAllWards();

      //THEN
      assertThat(actual, Matchers.empty());
    }

  }

}
