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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.wirvsvirus.trackyourbed.dto.response.BedStateDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.BedStateDtoMapper;
import de.wirvsvirus.trackyourbed.entity.BedState;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedStateException;
import de.wirvsvirus.trackyourbed.persistence.BedStateRepository;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Tests for BedStateService")
class BedStateServiceTest {

  @Nested
  @DisplayName("Test calls to getBedSTateByName")
  class GetBedStateByNameTest {

    @Test
    @DisplayName("Should call dependencies and return expected BedState if bed state exists.")
    void shouldCallDependenciesAndReturnExpectedResultWhenCalled () {
      // GIVEN
      final String name = "name";
      final BedStateRepository bedStateRepository = mock(BedStateRepository.class);
      final BedState bedState = new BedState().setName(name);
      when(bedStateRepository.findByName(anyString())).thenReturn(Optional.of(bedState));

      final BedStateDtoMapper bedStateDtoMapper = mock(BedStateDtoMapper.class);
      final BedStateDto expected = new BedStateDto().setName(name);
      when(bedStateDtoMapper.entityToDto(any(BedState.class))).thenReturn(expected);

      // WHEN
      final BedStateDto actual =
          new BedStateService(bedStateRepository, bedStateDtoMapper).getBedStateByName(name);

      // THEN
      assertSame(expected, actual);

      verify(bedStateRepository).findByName(eq(name));
      verify(bedStateDtoMapper).entityToDto(argThat(state -> {
        assertEquals(name, state.getName());
        return true;
      }));
    }

    @Test
    @DisplayName("Should throw a NoSuchBedTypeException when if bed state does not exist.")
    void shouldThrowNoSuchBedExceptionWhenBedStateDoesNotExist () {
      // GIVEN
      final String name = "name";
      final BedStateRepository bedStateRepository = mock(BedStateRepository.class);
      when(bedStateRepository.findByName(anyString())).thenReturn(Optional.empty());
      final String expectedMessage = String.format(NoSuchBedStateException.MESSAGE_TEMPLATE, name);

      // WHEN
      final NoSuchBedStateException e = assertThrows(NoSuchBedStateException.class,
          () -> new BedStateService(bedStateRepository, null).getBedStateByName(name));

      // THEN
      assertNotNull(e);
      assertEquals(expectedMessage, e.getMessage());
    }
  }

  @Nested
  class GetAllBedStatesTest {

    @Test
    @DisplayName("Should call dependencies and return a collection of all existing bed types")
    void shouldCallDependenciesAndReturnCollectionOfBedTypesWhenCalled() {
      // GIVEN
      final BedStateRepository bedStateRepository = mock(BedStateRepository.class);
      final String bedStateOneName = "one";
      final String bedStateTwoName = "two";
      final Collection<BedState> allBedStates = List.of(
          new BedState().setName(bedStateOneName),
          new BedState().setName(bedStateTwoName)
      );
      when(bedStateRepository.findAll()).thenReturn(allBedStates);

      final BedStateDtoMapper bedStateDtoMapper = mock(BedStateDtoMapper.class);
      when(bedStateDtoMapper.entityToDto(any(BedState.class)))
          .thenAnswer(arguments -> new BedStateDto()
              .setName(arguments.getArgument(0, BedState.class).getName())
          );

      final List<String> expectedBedStateNames = List.of(bedStateOneName, bedStateTwoName);
      // WHEN
      final Collection<BedStateDto> actual =
          new BedStateService(bedStateRepository, bedStateDtoMapper).getAllBedStates();

      // THEN
      assertThat(actual, hasSize(2));
      assertThat(
          actual.stream()
              .map(BedStateDto::getName)
              .collect(Collectors.toList()),
          containsInAnyOrder(expectedBedStateNames.toArray()));

      verify(bedStateDtoMapper, times(1))
          .entityToDto(argThat(state -> Objects.equals(bedStateOneName, state.getName())));
      verify(bedStateDtoMapper, times(1))
          .entityToDto(argThat(state -> Objects.equals(bedStateTwoName, state.getName())));
    }
  }
}