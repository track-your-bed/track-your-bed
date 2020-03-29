package de.wirvsvirus.trackyourbed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
      final WardTypeDto actual =
          new WardTypeService(wardTypeRepository, wardTypeDtoMapper).getWardTypeByName(name);

      // THEN
      assertSame(expected, actual);

      verify(wardTypeRepository).findByName(eq(name));
      verify(wardTypeDtoMapper).entityToDto(same(wardType));
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


      for (final String name: wardTypeNames) {
        final WardType wardType = new WardType().setName(name);
        final WardTypeDto wardTypeDto = new WardTypeDto().setName(name);
        wardTypes.add(wardType);
        expected.add(wardTypeDto);
      }

      final WardTypeRepository wardTypeRepository = mock(WardTypeRepository.class);
      when(wardTypeRepository.findAll()).thenReturn(wardTypes);

      final WardTypeDtoMapper wardTypeDtoMapper = mock(WardTypeDtoMapper.class);
      when(wardTypeDtoMapper.entitiesToDtos(anyList())).thenReturn(expected);

      //WHEN
      final Collection<WardTypeDto> actual =
          new WardTypeService(wardTypeRepository, wardTypeDtoMapper).getAllWardTypes();

      //THEN
      assertSame(expected, actual);

      verify(wardTypeRepository).findAll();
      verify(wardTypeDtoMapper).entitiesToDtos(same(wardTypes));

    }

    @Test
    void shouldNotFailOnEmptyRepository() {
      // GIVEN

      final List<WardType> wardTypes = new ArrayList<>();
      final WardTypeRepository wardTypeRepository = mock(WardTypeRepository.class);
      when(wardTypeRepository.findAll()).thenReturn(wardTypes);

      final List<WardTypeDto> expected = new ArrayList<>();
      final WardTypeDtoMapper wardTypeDtoMapper = mock(WardTypeDtoMapper.class);
      when(wardTypeDtoMapper.entitiesToDtos(anyList())).thenReturn(expected);

      //WHEN
      final Collection<WardTypeDto> actual =
          new WardTypeService(wardTypeRepository, wardTypeDtoMapper).getAllWardTypes();

      //THEN
      assertSame(expected, actual);

      verify(wardTypeRepository).findAll();
      verify(wardTypeDtoMapper).entitiesToDtos(same(wardTypes));

    }

  }

}
