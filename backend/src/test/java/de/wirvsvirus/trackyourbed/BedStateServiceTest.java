package de.wirvsvirus.trackyourbed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.wirvsvirus.trackyourbed.dto.response.BedStateDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.BedStateDtoMapper;
import de.wirvsvirus.trackyourbed.entity.BedState;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedStateException;
import de.wirvsvirus.trackyourbed.persistence.BedStateRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class BedStateServiceTest {

  @Test
  void shouldCallRepositoryAndReturnExpectedResultWhenGetBedStateByNameIsCalled() {
    // GIVEN
    final String name = "name";
    final BedStateRepository bedStateRepository = mock(BedStateRepository.class);
    final BedState bedState = new BedState().setName(name);
    when(bedStateRepository.findByName(anyString())).thenReturn(Optional.of(bedState));

    final BedStateDtoMapper bedStateDtoMapper = mock(BedStateDtoMapper.class);
    final BedStateDto expected = new BedStateDto().setName(name);
    when(bedStateDtoMapper.entityToDto(any(BedState.class))).thenReturn(expected);

    // WHEN
    final BedStateDto actual = new BedStateService(bedStateRepository, bedStateDtoMapper)
        .getBedStateByName(name);

    // THEN
    assertSame(expected, actual);

    verify(bedStateRepository).findByName(eq(name));
    verify(bedStateDtoMapper).entityToDto(argThat(state -> {
      assertEquals(name, state.getName());
      return true;
    }));
  }

  @Test
  void shouldThrowNoSuchBedExceptionWhenBedStateDoesNotExist() {
    // GIVEN
    final String name = "name";
    final BedStateRepository bedStateRepository = mock(BedStateRepository.class);
    when(bedStateRepository.findByName(anyString())).thenReturn(Optional.empty());
    final String expectedMessage = String.format(NoSuchBedStateException.MESSAGE_TEMPLATE, name);

    // WHEN
    final NoSuchBedStateException e = assertThrows(
        NoSuchBedStateException.class,
        () -> new BedStateService(bedStateRepository, null).getBedStateByName(name)
    );

    // THEN
    assertNotNull(e);
    assertEquals(expectedMessage, e.getMessage());
  }

}