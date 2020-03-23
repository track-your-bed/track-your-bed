package de.wirvsvirus.trackyourbed;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.wirvsvirus.trackyourbed.dto.response.WardTypeDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.WardTypeDtoMapper;
import de.wirvsvirus.trackyourbed.entity.WardType;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchWardTypeException;
import de.wirvsvirus.trackyourbed.persistence.WardTypeRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@DisplayName("Test WardTypeService")
class WardTypeServiceTest {

  @Nested
  @DisplayName("Test calls to getWardTypeByName")
  class GetWardTypeByNameTest {

    @Test
    void shouldCallRepositoryAndReturnExpectedResultWhenCalled () {
      // GIVEN
      final String name = "name";
      final WardTypeRepository wardTypeRepository = mock(WardTypeRepository.class);
      final WardType wardType = new WardType().setName(name);
      when(wardTypeRepository.findByName(anyString())).thenReturn(Optional.of(wardType));

      final WardTypeDtoMapper wardTypeDtoMapper = mock(WardTypeDtoMapper.class);
      final WardTypeDto expected = new WardTypeDto().setName(name);
      when(wardTypeDtoMapper.entityToDto(any(WardType.class))).thenReturn(expected);

      // WHEN
      final WardTypeDto actual = new WardTypeService(wardTypeRepository, wardTypeDtoMapper).getWardTypeByName(name);

      // THEN
      assertSame(expected, actual);

      verify(wardTypeRepository).findByName(eq(name));
      verify(wardTypeDtoMapper).entityToDto(argThat(state -> {
        assertEquals(name, state.getName());
        return true;
      }));
    }

    @Test
    void shouldThrowNoSuchBedExceptionWhenWardTypeDoesNotExist () {
      // GIVEN
      final String name = "name";
      final WardTypeRepository wardTypeRepository = mock(WardTypeRepository.class);
      when(wardTypeRepository.findByName(anyString())).thenReturn(Optional.empty());
      final String expectedMessage = String.format(NoSuchWardTypeException.MESSAGE_TEMPLATE, name);

      // WHEN
      final NoSuchWardTypeException e = assertThrows(NoSuchWardTypeException.class,
          () -> new WardTypeService(wardTypeRepository, null).getWardTypeByName(name));

      // THEN
      assertNotNull(e);
      assertEquals(expectedMessage, e.getMessage());
    }
  }

  @Nested
  @DisplayName("Test calls to getAllWardTypes")
  class GetAllWardTypesTest {
    @Test
    void shouldFetchAllWardTypesFromRepository() {
      // GIVEN
      final List<String> wardTypeNames = Arrays.asList("name1", "name2");
      final List<WardType> wardTypes = new ArrayList<>();
      final List<WardTypeDto> expected = new ArrayList<>();
      final WardTypeDtoMapper wardTypeDtoMapper = mock(WardTypeDtoMapper.class);

      for (final String name: wardTypeNames) {
          final WardType wardType = new WardType().setName(name);
          final WardTypeDto wardTypeDto = new WardTypeDto().setName(name);
          when(wardTypeDtoMapper.entityToDto(refEq(wardType))).thenReturn(wardTypeDto);
          wardTypes.add(wardType);
          expected.add(wardTypeDto);
      }

      final WardTypeRepository wardTypeRepository = mock(WardTypeRepository.class);
      when(wardTypeRepository.findAll()).thenReturn(wardTypes);

      WardTypeService wardTypeService = new WardTypeService(wardTypeRepository, wardTypeDtoMapper);

      //WHEN
      final Collection<WardTypeDto> actual = wardTypeService.getAllWardTypes();

      //THEN
      assertThat(actual, Matchers.containsInAnyOrder(expected.toArray()));
      verify(wardTypeRepository).findAll();
      for (final WardType wardType : wardTypes) {
        verify(wardTypeDtoMapper).entityToDto(refEq(wardType));
      }

    }

    @Test
    void shouldNotFailOnEmptyRepository() {
      // GIVEN
      final WardTypeDtoMapper wardTypeDtoMapper = mock(WardTypeDtoMapper.class);

      final WardTypeRepository wardTypeRepository = mock(WardTypeRepository.class);
      when(wardTypeRepository.findAll()).thenReturn(new ArrayList<>());

      WardTypeService wardTypeService = new WardTypeService(wardTypeRepository, wardTypeDtoMapper);

      //WHEN
      final Collection<WardTypeDto> actual = wardTypeService.getAllWardTypes();

      //THEN
      assertThat(actual, Matchers.empty());
      verify(wardTypeRepository).findAll();

    }

  }

}
