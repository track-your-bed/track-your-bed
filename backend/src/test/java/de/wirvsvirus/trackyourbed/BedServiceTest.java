package de.wirvsvirus.trackyourbed;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewBed;
import de.wirvsvirus.trackyourbed.dto.request.UpdateBed;
import de.wirvsvirus.trackyourbed.dto.request.mapper.CreateNewBedMapper;
import de.wirvsvirus.trackyourbed.dto.response.BedDto;
import de.wirvsvirus.trackyourbed.dto.response.BedStateDto;
import de.wirvsvirus.trackyourbed.dto.response.BedTypeDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.BedDtoMapper;
import de.wirvsvirus.trackyourbed.dto.response.mapper.BedStateDtoMapper;
import de.wirvsvirus.trackyourbed.dto.response.mapper.BedTypeDtoMapper;
import de.wirvsvirus.trackyourbed.entity.Bed;
import de.wirvsvirus.trackyourbed.entity.BedState;
import de.wirvsvirus.trackyourbed.entity.BedType;
import de.wirvsvirus.trackyourbed.entity.Ward;
import de.wirvsvirus.trackyourbed.excpetion.dependency.InvalidBedStateException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.InvalidBedTypeException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.WardMissingException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedException;
import de.wirvsvirus.trackyourbed.persistence.BedRepository;
import de.wirvsvirus.trackyourbed.persistence.BedStateRepository;
import de.wirvsvirus.trackyourbed.persistence.BedTypeRepository;
import de.wirvsvirus.trackyourbed.persistence.WardRepository;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Tests for BedStateService")
class BedServiceTest {

  @Nested
  @DisplayName("Test calls to createNewBed")
  class CreateNewBedTests {

    @Test
    @DisplayName("Should call dependencies and return expected result when called.")
    void shouldCallDependenciesAndReturnExpectedResultWhenCalled() {
      // GIVEN
      final String state = "state";
      final String type = "type";
      final UUID wardId = UUID.randomUUID();
      final Ward ward = new Ward();
      ward.setId(wardId);
      final CreateNewBed request = new CreateNewBed()
          .setBedType(type)
          .setBedState(state)
          .setWardId(wardId);
      final Bed bed = new Bed();
      final CreateNewBedMapper createNewBedMapper = mock(CreateNewBedMapper.class);
      when(createNewBedMapper.dtoToEntity(request)).thenReturn(bed);

      final WardRepository wardRepository = mock(WardRepository.class);
      when(wardRepository.findById(wardId)).thenReturn(Optional.of(ward));

      final BedType bedType = new BedType().setName(type);
      final BedTypeRepository bedTypeRepository = mock(BedTypeRepository.class);
      when(bedTypeRepository.findByName(anyString())).thenReturn(Optional.of(bedType));

      final BedState bedState = new BedState().setName(state);
      final BedStateRepository bedStateRepository = mock(BedStateRepository.class);
      when(bedStateRepository.findByName(state)).thenReturn(Optional.of(bedState));
      final BedRepository bedRepository = mock(BedRepository.class);
      when(bedRepository.save(any(Bed.class))).thenReturn(bed);

      final BedDto expected = new BedDto();
      final BedDtoMapper bedDtoMapper = mock(BedDtoMapper.class);
      when(bedDtoMapper.entityToDto(any(Bed.class))).thenReturn(expected);

      // WHEN
      final BedDto actual = new BedService(
          bedRepository,
          wardRepository,
          bedTypeRepository,
          bedStateRepository,
          createNewBedMapper,
          bedDtoMapper,
          null,
          null
      ).createNewBed(request);

      // THEN
      assertSame(expected, actual);
      verify(createNewBedMapper).dtoToEntity(argThat(r -> {
        assertSame(request, r);
        return true;
      }));
      verify(wardRepository).findById(eq(wardId));
      verify(bedTypeRepository).findByName(eq(type));
      verify(bedStateRepository).findByName(eq(state));
      verify(bedRepository).save(argThat(b -> {
        assertSame(bed, b);
        return true;
      }));
      verify(bedDtoMapper).entityToDto(argThat(b -> {
        assertSame(bed, b);
        return true;
      }));
    }

    @Test
    @DisplayName("Should throw a WardMissingException when the ward is not found.")
    void shouldThrowWardMissingExceptionWhenWardIsNotFound() {
      // GIVEN
      final String state = "state";
      final String type = "type";
      final UUID wardId = UUID.randomUUID();
      final Ward ward = new Ward();
      ward.setId(wardId);
      final CreateNewBed request = new CreateNewBed()
          .setBedType(type)
          .setBedState(state)
          .setWardId(wardId);
      final Bed bed = new Bed();
      final CreateNewBedMapper createNewBedMapper = mock(CreateNewBedMapper.class);
      when(createNewBedMapper.dtoToEntity(request)).thenReturn(bed);

      final WardRepository wardRepository = mock(WardRepository.class);
      when(wardRepository.findById(wardId)).thenReturn(Optional.empty());
      final String expectedMessage = String.format(WardMissingException.MESSAGE_TEMPLATE, wardId);

      // WHEN
      final WardMissingException e = assertThrows(
          WardMissingException.class,
          () -> new BedService(
              null,
              wardRepository,
              null,
              null,
              createNewBedMapper,
              null,
              null,
              null
          ).createNewBed(request)
      );

      // THEN
      assertNotNull(e);
      assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    @DisplayName("Should throw an InvalidBedTypeException when the specified bed type is not found.")
    void shouldThrowInvalidBedTypeExceptionWhenBedTypeIsNotFound() {
      // GIVEN
      final String type = "type";
      final UUID wardId = UUID.randomUUID();
      final Ward ward = new Ward();
      ward.setId(wardId);
      final CreateNewBed request = new CreateNewBed()
          .setBedType(type)
          .setWardId(wardId);
      final Bed bed = new Bed();
      final CreateNewBedMapper createNewBedMapper = mock(CreateNewBedMapper.class);
      when(createNewBedMapper.dtoToEntity(request)).thenReturn(bed);

      final WardRepository wardRepository = mock(WardRepository.class);
      when(wardRepository.findById(wardId)).thenReturn(Optional.of(ward));

      final BedTypeRepository bedTypeRepository = mock(BedTypeRepository.class);
      when(bedTypeRepository.findByName(anyString())).thenReturn(Optional.empty());

      final String expected = String.format(InvalidBedTypeException.MESSAGE_TEMPLATE, type);

      // WHEN
      final InvalidBedTypeException e = assertThrows(
          InvalidBedTypeException.class,
          () -> new BedService(
              null,
              wardRepository,
              bedTypeRepository,
              null,
              createNewBedMapper,
              null,
              null,
              null
          ).createNewBed(request)
      );

      // THEN
      assertEquals(expected, e.getMessage());
    }

    @Test
    @DisplayName("Should throw an InvalidBedStateException when the bed state is not found.")
    void shouldThrowInvalidBedStateExceptionWhenBedStateIsNotFound() {
      // GIVEN
      final String state = "state";
      final String type = "type";
      final UUID wardId = UUID.randomUUID();
      final Ward ward = new Ward();
      ward.setId(wardId);
      final CreateNewBed request = new CreateNewBed()
          .setBedType(type)
          .setBedState(state)
          .setWardId(wardId);
      final Bed bed = new Bed();
      final CreateNewBedMapper createNewBedMapper = mock(CreateNewBedMapper.class);
      when(createNewBedMapper.dtoToEntity(request)).thenReturn(bed);

      final WardRepository wardRepository = mock(WardRepository.class);
      when(wardRepository.findById(wardId)).thenReturn(Optional.of(ward));

      final BedType bedType = new BedType().setName(type);
      final BedTypeRepository bedTypeRepository = mock(BedTypeRepository.class);
      when(bedTypeRepository.findByName(anyString())).thenReturn(Optional.of(bedType));

      final BedStateRepository bedStateRepository = mock(BedStateRepository.class);
      when(bedStateRepository.findByName(state)).thenReturn(Optional.empty());

      final String expectedMessage =
          String.format(InvalidBedStateException.MESSAGE_TEMPLATE, state);

      // WHEN
      final InvalidBedStateException e = assertThrows(
          InvalidBedStateException.class,
          () -> new BedService(
              null,
              wardRepository,
              bedTypeRepository,
              bedStateRepository,
              createNewBedMapper,
              null,
              null,
              null
          ).createNewBed(request)
      );

      // THEN
      assertEquals(expectedMessage, e.getMessage());
    }

  }

  @Nested
  @DisplayName("Test calls to getAllBeds")
  class GetAllBedsTest {

    @Test
    @DisplayName("Should call dependencies and return the expected result when called.")
    void shouldCallDependenciesAndReturnExpectedResultWhenCalled() {
      // GIVEN
      final String bedOneName = "bedOneName";
      final String bedTwoName = "bedTwoName";
      final List<Bed> allBeds = List.of(
          new Bed().setName(bedOneName),
          new Bed().setName(bedTwoName)
      );
      final BedRepository bedRepository = mock(BedRepository.class);
      when(bedRepository.findAll()).thenReturn(allBeds);

      final BedDtoMapper bedDtoMapper = mock(BedDtoMapper.class);
      when(bedDtoMapper.entityToDto(any(Bed.class)))
          .thenAnswer(arguments -> new BedDto()
              .setName(arguments.getArgument(0, Bed.class).getName())
          );
      final List<String> expectedBedNames = List.of(bedOneName, bedTwoName);

      // WHEN
      final Collection<BedDto> actual = new BedService(
          bedRepository,
          null,
          null,
          null,
          null,
          bedDtoMapper,
          null,
          null
      ).getAllBeds();

      // THEN
      assertNotNull(actual);
      assertThat(actual, hasSize(2));
      assertThat(
          actual.stream().map(BedDto::getName).collect(Collectors.toList()),
          containsInAnyOrder(expectedBedNames.toArray())
      );

      verify(bedRepository).findAll();
      verify(bedDtoMapper).entityToDto(argThat(b -> Objects.equals(bedOneName, b.getName())));
      verify(bedDtoMapper).entityToDto(argThat(b -> Objects.equals(bedTwoName, b.getName())));
    }

  }

  @Nested
  @DisplayName("Test calls to getBedById")
  class GetBedByIdTest {

    @Test
    @DisplayName("Should call dependencies and return the expected result when called.")
    void shouldCallDependenciesAndReturnExpectedResultWhenCalled() {
      // GIVEN
      final UUID bedId = UUID.randomUUID();
      final Bed bed = new Bed();
      final BedRepository bedRepository = mock(BedRepository.class);
      when(bedRepository.findById(any(UUID.class))).thenReturn(Optional.of(bed));

      final BedDto expected = new BedDto();
      final BedDtoMapper bedDtoMapper = mock(BedDtoMapper.class);
      when(bedDtoMapper.entityToDto(any(Bed.class))).thenReturn(expected);

      // WHEN
      final BedDto actual = new BedService(
          bedRepository,
          null,
          null,
          null,
          null,
          bedDtoMapper,
          null,
          null
      ).getBedById(bedId);

      // THEN
      assertNotNull(actual);
      assertSame(expected, actual);

      verify(bedRepository).findById(eq(bedId));
      verify(bedDtoMapper).entityToDto(same(bed));
    }

    @Test
    @DisplayName("Should throw a NoSuchBedException when no bed for the given id is found.")
    void shouldThrowNoSuchBedExceptionWhenNoBedIsFound() {
      // GIVEN
      final UUID bedId = UUID.randomUUID();
      final BedRepository bedRepository = mock(BedRepository.class);
      when(bedRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

      final String expectedMessage = String.format(NoSuchBedException.MESSAGE_TEMPLATE, bedId);

      // WHEN
      final NoSuchBedException e = assertThrows(
          NoSuchBedException.class,
          () -> new BedService(
              bedRepository,
              null,
              null,
              null,
              null,
              null,
              null,
              null
          ).getBedById(bedId)
      );

      // THEN
      assertNotNull(e);
      assertEquals(expectedMessage, e.getMessage());
    }
  }

  @Nested
  @DisplayName("Test calls to updateBed")
  class UpdateBedTest {

    @Test
    @DisplayName("Should call dependencies and return the expected result when called.")
    void shouldCallDependenciesAndReturnExpectedResultWhenCalled() {
      // GIVEN
      final UUID bedId = UUID.randomUUID();
      final Bed bedBeforeChange = new Bed();
      final BedRepository bedRepository = mock(BedRepository.class);
      when(bedRepository.findById(any(UUID.class))).thenReturn(Optional.of(bedBeforeChange));

      final UUID wardId = UUID.randomUUID();
      final Ward ward = new Ward();
      final WardRepository wardRepository = mock(WardRepository.class);
      when(wardRepository.findById(any(UUID.class))).thenReturn(Optional.of(ward));

      final String type = "type";
      final BedType bedType = new BedType().setName(type);
      final BedTypeRepository bedTypeRepository = mock(BedTypeRepository.class);
      when(bedTypeRepository.findByName(anyString())).thenReturn(Optional.of(bedType));

      final String state = "state";
      final BedState bedState = new BedState().setName(state);
      final BedStateRepository bedStateRepository = mock(BedStateRepository.class);
      when(bedStateRepository.findByName(anyString())).thenReturn(Optional.of(bedState));

      final Bed bedAfterChange = new Bed();
      when(bedRepository.save(any(Bed.class))).thenReturn(bedAfterChange);

      final BedDto expected = new BedDto();
      final BedDtoMapper bedDtoMapper = mock(BedDtoMapper.class);
      when(bedDtoMapper.entityToDto(any(Bed.class))).thenReturn(expected);

      final String newName = "new name";
      final UpdateBed request = new UpdateBed()
          .setName("new name")
          .setWardId(wardId)
          .setBedType(type)
          .setBedState(state);

      // WHEN
      final BedDto actual = new BedService(
          bedRepository,
          wardRepository,
          bedTypeRepository,
          bedStateRepository,
          null,
          bedDtoMapper,
          null,
          null
      ).updateBed(bedId, request);

      // THEN
      assertSame(expected, actual);

      verify(bedRepository).findById(eq(bedId));
      verify(wardRepository).findById(eq(wardId));
      verify(bedTypeRepository).findByName(eq(type));
      verify(bedStateRepository).findByName(eq(state));
      verify(bedRepository).save(argThat(newBed -> {
        assertEquals(newName, newBed.getName());
        assertSame(ward, newBed.getWard());
        assertSame(bedType, newBed.getBedType());
        assertSame(bedState, newBed.getBedState());
        return true;
      }));
    }

    @Test
    @DisplayName("Should throw a WardMissingException when the ward is not found.")
    void shouldThrowWardMissingExceptionWhenWardIsNotFound() {
      // GIVEN
      final UUID bedId = UUID.randomUUID();
      final Bed bedBeforeChange = new Bed();
      final BedRepository bedRepository = mock(BedRepository.class);
      when(bedRepository.findById(any(UUID.class))).thenReturn(Optional.of(bedBeforeChange));

      final WardRepository wardRepository = mock(WardRepository.class);
      when(wardRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

      final UUID wardId = UUID.randomUUID();
      final UpdateBed request = new UpdateBed()
          .setName("new name")
          .setWardId(wardId);
      final String expectedMessage = String.format(WardMissingException.MESSAGE_TEMPLATE, wardId);

      // WHEN
      final WardMissingException e = assertThrows(
          WardMissingException.class,
          () -> new BedService(
              bedRepository,
              wardRepository,
              null,
              null,
              null,
              null,
              null,
              null
          ).updateBed(bedId, request)
      );

      // THEN
      assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    @DisplayName("Should throw an InvalidBedTypeException when bed type is not found.")
    void shouldThrowInvalidBedTypeExceptionWhenBedTypeIsNotFound() {
      // GIVEN
      final UUID bedId = UUID.randomUUID();
      final Bed bedBeforeChange = new Bed();

      final BedRepository bedRepository = mock(BedRepository.class);
      when(bedRepository.findById(any(UUID.class))).thenReturn(Optional.of(bedBeforeChange));

      final UUID wardId = UUID.randomUUID();
      final Ward ward = new Ward();
      final WardRepository wardRepository = mock(WardRepository.class);
      when(wardRepository.findById(any(UUID.class))).thenReturn(Optional.of(ward));

      final String type = "type";
      final BedTypeRepository bedTypeRepository = mock(BedTypeRepository.class);
      when(bedTypeRepository.findByName(anyString())).thenReturn(Optional.empty());

      final UpdateBed request = new UpdateBed()
          .setName("new name")
          .setWardId(wardId)
          .setBedType(type);

      final String expectedMessage = String.format(InvalidBedTypeException.MESSAGE_TEMPLATE, type);

      // WHEN
      final InvalidBedTypeException e = assertThrows(
          InvalidBedTypeException.class,
          () -> new BedService(
              bedRepository,
              wardRepository,
              bedTypeRepository,
              null,
              null,
              null,
              null,
              null
          ).updateBed(bedId, request));

      // THEN
      assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    @DisplayName("Should throw an InvalidBedStateException when bed state is not found.")
    void shouldThrowInvalidBedStateExceptionWhenBedStateIsNotFound() {
      // GIVEN
      final UUID bedId = UUID.randomUUID();
      final Bed bedBeforeChange = new Bed();
      final BedRepository bedRepository = mock(BedRepository.class);
      when(bedRepository.findById(any(UUID.class))).thenReturn(Optional.of(bedBeforeChange));

      final UUID wardId = UUID.randomUUID();
      final Ward ward = new Ward();
      final WardRepository wardRepository = mock(WardRepository.class);
      when(wardRepository.findById(any(UUID.class))).thenReturn(Optional.of(ward));

      final String type = "type";
      final BedType bedType = new BedType().setName(type);
      final BedTypeRepository bedTypeRepository = mock(BedTypeRepository.class);
      when(bedTypeRepository.findByName(anyString())).thenReturn(Optional.of(bedType));

      final String state = "state";
      final BedStateRepository bedStateRepository = mock(BedStateRepository.class);
      when(bedStateRepository.findByName(anyString())).thenReturn(Optional.empty());

      final String newName = "new name";
      final UpdateBed request = new UpdateBed()
          .setName("new name")
          .setWardId(wardId)
          .setBedType(type)
          .setBedState(state);

      final String expectedMessage =
          String.format(InvalidBedStateException.MESSAGE_TEMPLATE, state);

      // WHEN
      final InvalidBedStateException e = assertThrows(
          InvalidBedStateException.class,
          () -> new BedService(
              bedRepository,
              wardRepository,
              bedTypeRepository,
              bedStateRepository,
              null,
              null,
              null,
              null
          ).updateBed(bedId, request));

      // THEN
      assertEquals(expectedMessage, e.getMessage());
    }

  }

  @Nested
  @DisplayName("Test calls to deleteBedById.")
  class DeleteBedByIdTest {

    @Test
    @DisplayName("Should call bedRepository and return the expected result when called.")
    void shouldCallBedRepositoryAndReturnExpectedResultWhenCalled() {
      // GIVEN
      final UUID id = UUID.randomUUID();
      final BedRepository bedRepository = mock(BedRepository.class);

      // WHEN
      new BedService(bedRepository, null, null, null, null, null, null, null).deleteBedById(id);

      // THEN
      verify(bedRepository).deleteById(eq(id));
    }
  }

  @Nested
  @DisplayName("Test calls to updateState.")
  class UpdateStateTest {

    @Test
    @DisplayName("Should call dependencies and return the expected result when called.")
    void shouldCallDependenciesAndReturnExpectedResultWhenCalled() {
      // GIVEN
      final UUID id = UUID.randomUUID();
      final BedRepository bedRepository = mock(BedRepository.class);
      final Bed bed = new Bed();
      when(bedRepository.findById(any(UUID.class))).thenReturn(Optional.of(bed));

      final String state = "state";
      final BedState bedState = new BedState();
      final BedStateRepository bedStateRepository = mock(BedStateRepository.class);
      when(bedStateRepository.findByName(anyString())).thenReturn(Optional.of(bedState));

      final Bed updated = new Bed().setBedState(bedState);
      when(bedRepository.save(any(Bed.class))).thenReturn(updated);

      final BedStateDto expected = new BedStateDto();
      final BedStateDtoMapper bedStateDtoMapper = mock(BedStateDtoMapper.class);
      when(bedStateDtoMapper.entityToDto(any(BedState.class))).thenReturn(expected);

      // WHEN
      final BedStateDto actual = new BedService(
          bedRepository,
          null,
          null,
          bedStateRepository,
          null,
          null,
          bedStateDtoMapper,
          null
      ).updateState(id, state);

      // THEN
      assertSame(expected, actual);

      verify(bedRepository).findById(eq(id));
      verify(bedStateRepository).findByName(eq(state));
      verify(bedRepository).save(argThat(b -> {
        assertSame(bedState, b.getBedState());
        return true;
      }));
      verify(bedStateDtoMapper).entityToDto(same(bedState));
    }

    @Test
    @DisplayName("Should throw NoSuchBedException when bed is not found.")
    void shouldThrowNoSuchBedExceptionWhenBedIsNotFound() {
      // GIVEN
      final UUID id = UUID.randomUUID();
      final BedRepository bedRepository = mock(BedRepository.class);
      when(bedRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

      final String state = "state";

      final String expectedMessage = String.format(NoSuchBedException.MESSAGE_TEMPLATE, id);

      // WHEN
      final NoSuchBedException e = assertThrows(
          NoSuchBedException.class,
          () -> new BedService(
              bedRepository,
              null,
              null,
              null,
              null,
              null,
              null,
              null
          ).updateState(id, state));

      // THEN
      assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    @DisplayName("Should throw an InvalidBedStateException when bed state is not found.")
    void shouldThrowInvalidBedStateExceptionWhenBedStateIsNotFound() {
      // GIVEN
      final UUID id = UUID.randomUUID();
      final BedRepository bedRepository = mock(BedRepository.class);
      final Bed bed = new Bed();
      when(bedRepository.findById(any(UUID.class))).thenReturn(Optional.of(bed));

      final String state = "state";
      final BedStateRepository bedStateRepository = mock(BedStateRepository.class);
      when(bedStateRepository.findByName(anyString())).thenReturn(Optional.empty());

      final String expectedMessage =
          String.format(InvalidBedStateException.MESSAGE_TEMPLATE, state);

      // WHEN
      final InvalidBedStateException e = assertThrows(
          InvalidBedStateException.class,
          () -> new BedService(
              bedRepository,
              null,
              null,
              bedStateRepository,
              null,
              null,
              null,
              null
          ).updateState(id, state));

      // THEN
      assertEquals(expectedMessage, e.getMessage());
    }


  }

  @Nested
  @DisplayName("Test calls to updateType.")
  class UpdateTypeTest {

    @Test
    @DisplayName("Should call dependencies and return the expected result when called.")
    void shouldCallDependenciesAndReturnExpectedResultWhenCalled() {
      // GIVEN
      final UUID id = UUID.randomUUID();
      final BedRepository bedRepository = mock(BedRepository.class);
      final Bed bed = new Bed();
      when(bedRepository.findById(any(UUID.class))).thenReturn(Optional.of(bed));

      final String type = "type";
      final BedType bedType = new BedType();
      final BedTypeRepository bedTypeRepository = mock(BedTypeRepository.class);
      when(bedTypeRepository.findByName(anyString())).thenReturn(Optional.of(bedType));

      final Bed updated = new Bed().setBedType(bedType);
      when(bedRepository.save(any(Bed.class))).thenReturn(updated);

      final BedTypeDto expected = new BedTypeDto();
      final BedTypeDtoMapper bedTypeDtoMapper = mock(BedTypeDtoMapper.class);
      when(bedTypeDtoMapper.entityToDto(any(BedType.class))).thenReturn(expected);

      // WHEN
      final BedTypeDto actual = new BedService(
          bedRepository,
          null,
          bedTypeRepository,
          null,
          null,
          null,
          null,
          bedTypeDtoMapper
      ).updateType(id, type);

      // THEN
      assertSame(expected, actual);

      verify(bedRepository).findById(eq(id));
      verify(bedTypeRepository).findByName(eq(type));
      verify(bedRepository).save(argThat(b -> {
        assertSame(bedType, b.getBedType());
        return true;
      }));
      verify(bedTypeDtoMapper).entityToDto(eq(bedType));
    }

    @Test
    @DisplayName("Should throw NoSuchBedException when bed is not found.")
    void shouldThrowNoSuchBedExceptionWhenBedIsNotFound() {
      // GIVEN
      final UUID id = UUID.randomUUID();
      final BedRepository bedRepository = mock(BedRepository.class);
      when(bedRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

      final String type = "type";

      final String expectedMessage = String.format(NoSuchBedException.MESSAGE_TEMPLATE, id);

      // WHEN
      final NoSuchBedException e = assertThrows(
          NoSuchBedException.class,
          () -> new BedService(
              bedRepository,
              null,
              null,
              null,
              null,
              null,
              null,
              null
          ).updateType(id, type));

      // THEN
      assertEquals(expectedMessage, e.getMessage());
    }

  }

  @Test
  @DisplayName("Should throw InvalidBedTypeException when bed type is not found.")
  void shouldThrowInvalidBedTypeExceptionWhenBedTypeIsNotFound() {
    // GIVEN
    final UUID id = UUID.randomUUID();
    final BedRepository bedRepository = mock(BedRepository.class);
    final Bed bed = new Bed();
    when(bedRepository.findById(any(UUID.class))).thenReturn(Optional.of(bed));

    final String type = "type";
    final BedTypeRepository bedTypeRepository = mock(BedTypeRepository.class);
    when(bedTypeRepository.findByName(anyString())).thenReturn(Optional.empty());

    final String expectedMessage = String.format(InvalidBedTypeException.MESSAGE_TEMPLATE, type);

    // WHEN
    final InvalidBedTypeException e = assertThrows(
        InvalidBedTypeException.class,
        () -> new BedService(
            bedRepository,
            null,
            bedTypeRepository,
            null,
            null,
            null,
            null,
            null
        ).updateType(id, type));

    // THEN
    assertEquals(expectedMessage, e.getMessage());
  }

}