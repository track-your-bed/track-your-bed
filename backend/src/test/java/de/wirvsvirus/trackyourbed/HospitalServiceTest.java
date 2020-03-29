package de.wirvsvirus.trackyourbed;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import de.wirvsvirus.trackyourbed.dto.request.CreateNewHospital;
import de.wirvsvirus.trackyourbed.dto.request.UpdateHospital;
import de.wirvsvirus.trackyourbed.dto.request.mapper.CreateNewHospitalMapper;
import de.wirvsvirus.trackyourbed.dto.response.HospitalCapacityDto;
import de.wirvsvirus.trackyourbed.dto.response.HospitalDto;
import de.wirvsvirus.trackyourbed.dto.response.mapper.HospitalCapacityDtoMapper;
import de.wirvsvirus.trackyourbed.dto.response.mapper.HospitalDtoMapper;
import de.wirvsvirus.trackyourbed.entity.DepartmentCapacity;
import de.wirvsvirus.trackyourbed.entity.Hospital;
import de.wirvsvirus.trackyourbed.entity.HospitalCapacity;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchHospitalException;
import de.wirvsvirus.trackyourbed.persistence.HospitalRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Tests for HospitalService")
class HospitalServiceTest {

  private CreateNewHospitalMapper createNewHospitalMapper;

  private HospitalRepository hospitalRepository;

  private HospitalDtoMapper hospitalDtoMapper;

  private CapacityService capacityService;

  private HospitalCapacityDtoMapper hospitalCapacityDtoMapper;

  private HospitalService hospitalService;

  @BeforeEach
  private void setUp() {
    this.createNewHospitalMapper = Mockito.mock(CreateNewHospitalMapper.class);
    this.hospitalRepository = Mockito.mock(HospitalRepository.class);
    this.hospitalDtoMapper = Mockito.mock(HospitalDtoMapper.class);
    this.capacityService = Mockito.mock(CapacityService.class);
    this.hospitalCapacityDtoMapper = Mockito.mock(HospitalCapacityDtoMapper.class);

    this.hospitalService = new HospitalService(
        hospitalRepository,
        createNewHospitalMapper,
        hospitalDtoMapper,
        capacityService,
        hospitalCapacityDtoMapper);
  }

  @Nested
  @DisplayName("Test calls to createNewHospital")
  class createNewHospitalTest {

    @Test
    @DisplayName("Should call dependencies and return expected result when called.")
    void shouldCreateNewHospital() {
      // GIVEN
      final CreateNewHospital request = new CreateNewHospital();
      final Hospital hospital = new Hospital();
      final HospitalDto expected = new HospitalDto();

      when(createNewHospitalMapper.dtoToEntity(request)).thenReturn(hospital);
      when(hospitalRepository.save(any(Hospital.class))).thenReturn(hospital);
      when(hospitalDtoMapper.entityToDto(any(Hospital.class))).thenReturn(expected);

      // WHEN
      final HospitalDto actual = hospitalService.createHospital(request);

      // THEN
      assertSame(expected, actual);
      verify(createNewHospitalMapper).dtoToEntity(argThat(r -> {
        assertSame(request, r);
        return true;
      }));
      verify(hospitalRepository).save(argThat(b -> {
        assertSame(hospital, b);
        return true;
      }));
      verify(hospitalDtoMapper).entityToDto(argThat(b -> {
        assertSame(hospital, b);
        return true;
      }));
    }
  }

  @Nested
  @DisplayName("Test calls to deleteHospital")
  class deleteHospitalTest {

    @Test
    @DisplayName("Should delete hospital")
    void shouldDeleteHospital() {
      //Given
      final UUID hospitalId = UUID.randomUUID();

      //When
      hospitalService.deleteHospital(hospitalId);

      //then
      verify(hospitalRepository).deleteById(hospitalId);
    }
  }

  @Nested
  @DisplayName("Test calls to getHospitalById")
  class GetHospitalByIdTest {

    @Test
    void shouldGetHospitalById() {
      //Given
      final Hospital hospital = new Hospital();
      final HospitalDto expected = new HospitalDto();
      final UUID hospitalId = UUID.randomUUID();

      when(hospitalRepository.findById(hospitalId)).thenReturn(Optional.of(hospital));
      when(hospitalDtoMapper.entityToDto(hospital)).thenReturn(expected);

      //When
      HospitalDto actual = hospitalService.getHospitalById(hospitalId);

      //then
      assertSame(expected, actual);
      verify(hospitalRepository, times(1)).findById(hospitalId);
      verify(hospitalDtoMapper, times(1)).entityToDto(hospital);
    }

    @Test
    @DisplayName("Should throw exception when no hospital found by id")
    void shouldThrowNoSuchHospitalExceptionWhenGetHospitalById() {
      //Given
      final UUID hospitalId = UUID.randomUUID();

      when(hospitalRepository.findById(hospitalId)).thenReturn(Optional.empty());

      //When
      NoSuchHospitalException e = assertThrows(
          NoSuchHospitalException.class,
          () -> hospitalService.getHospitalById(hospitalId));

      //then
      assertEquals("Could not find hospital with ID " + hospitalId, e.getMessage());
      verify(hospitalRepository, times(1)).findById(hospitalId);
      verifyNoInteractions(hospitalDtoMapper);
    }
  }

  @Nested
  @DisplayName("Test calls to getAllHospitals")
  class GetAllHospitalsTest {

    @Test
    @DisplayName("Should return all hospitals.")
    void shouldGetAllHospitals() {
      //Given
      String hospitalOneName = "hos1";
      String hospitalTwoName = "hos2";
      final Hospital hospital1 = new Hospital();
      hospital1.setName(hospitalOneName);
      hospital1.setMaxCapacity(1);
      final Hospital hospital2 = new Hospital();
      hospital2.setName(hospitalTwoName);
      hospital2.setMaxCapacity(2);

      List<Hospital> hospitals = Arrays.asList(hospital1, hospital2);

      when(hospitalRepository.findAll()).thenReturn(hospitals);
      when(hospitalDtoMapper.entityToDto(any(Hospital.class))).thenAnswer(arguments -> {
        HospitalDto hospitalDto = new HospitalDto();
        hospitalDto.setName(arguments.getArgument(0, Hospital.class).getName());
        return hospitalDto;
      });
      final List<String> expectedHospitalNames = List.of(hospitalOneName, hospitalTwoName);

      //when
      Collection<HospitalDto> actual = hospitalService.getAllHospitals();

      //then
      assertEquals(2, actual.size());
      assertThat(
          actual.stream().map(HospitalDto::getName).collect(Collectors.toList()),
          containsInAnyOrder(expectedHospitalNames.toArray())
      );

      verify(hospitalRepository).findAll();
      verify(hospitalDtoMapper)
          .entityToDto(argThat(h -> Objects.equals(hospitalOneName, h.getName())));
      verify(hospitalDtoMapper)
          .entityToDto(argThat(h -> Objects.equals(hospitalTwoName, h.getName())));
    }
  }


  @Nested
  @DisplayName("Test calls to updateHospital")
  class updateHospitalTest {

    @Test
    @DisplayName("Should update a hospital.")
    void shouldUpdateHospital() {
      //Given
      final UUID uuid = UUID.randomUUID();
      final UpdateHospital updateHospital = new UpdateHospital();
      updateHospital.setLat("11.11");
      updateHospital.setName("newHos");
      updateHospital.setLon("12.1");
      updateHospital.setMaxCapacity(12);
      final Hospital toBeUpdated = new Hospital();

      HospitalDto expected = new HospitalDto();
      expected.setName(updateHospital.getName());
      expected.setLat(updateHospital.getLat());
      expected.setLon(updateHospital.getLon());
      expected.setMaxCapacity(updateHospital.getMaxCapacity());

      when(hospitalRepository.findById(uuid)).thenReturn(Optional.of(toBeUpdated));
      when(hospitalDtoMapper.entityToDto(any(Hospital.class))).thenReturn(expected);

      //When
      HospitalDto actual = hospitalService.updateHospital(uuid, updateHospital);

      //Then
      assertEquals(expected, actual);
      verify(hospitalRepository, times(1)).findById(uuid);
      verify(hospitalDtoMapper, times(1)).entityToDto(any(Hospital.class));
    }

    @Test
    @DisplayName("Should not update hospital when null fields.")
    void shouldNotUpdateHospital() {
      //Given
      final UUID uuid = UUID.randomUUID();
      final UpdateHospital updateHospital = new UpdateHospital();
      final Hospital toBeUpdated = new Hospital();
      toBeUpdated.setLat("11.11");
      toBeUpdated.setName("oldHos");
      toBeUpdated.setLon("12.1");
      toBeUpdated.setMaxCapacity(12);

      HospitalDto expected = new HospitalDto();
      expected.setName(toBeUpdated.getName());
      expected.setLat(toBeUpdated.getLat());
      expected.setLon(toBeUpdated.getLon());
      expected.setMaxCapacity(toBeUpdated.getMaxCapacity());

      when(hospitalRepository.findById(uuid)).thenReturn(Optional.of(toBeUpdated));
      when(hospitalDtoMapper.entityToDto(any(Hospital.class))).thenReturn(expected);

      //When
      HospitalDto actual = hospitalService.updateHospital(uuid, updateHospital);

      //Then
      assertEquals(expected, actual);
      verify(hospitalRepository, times(1)).findById(uuid);
      verify(hospitalDtoMapper, times(1)).entityToDto(any(Hospital.class));
    }

    @Test
    @DisplayName("Should throw exception when no hospital found for provided id to update.")
    void shouldThrowNoSuchHospitalExceptionWhenUpdateHospital() {
      //Given
      final UUID uuid = UUID.randomUUID();
      final UpdateHospital updateHospital = new UpdateHospital();
      updateHospital.setLat("11.11");
      updateHospital.setName("newHos");
      updateHospital.setLon("12.1");
      updateHospital.setMaxCapacity(12);

      when(hospitalRepository.findById(uuid)).thenReturn(Optional.empty());

      //When
      NoSuchHospitalException e = assertThrows(
          NoSuchHospitalException.class,
          () -> hospitalService.updateHospital(uuid, updateHospital));

      //Then
      assertEquals("Could not find hospital with ID " + uuid, e.getMessage());
      verify(hospitalRepository, times(1)).findById(uuid);
      verify(hospitalDtoMapper, times(0)).entityToDto(any(Hospital.class));
    }
  }

  @Nested
  @DisplayName("Test calls to deleteHospitalById")
  class deleteHospitalByIdTest {

    @Test
    @DisplayName("Should delete a hospital of given id.")
    void deleteHospitalById() {
      //Given
      final UUID id = UUID.randomUUID();

      //When
      hospitalService.deleteHospitalById(id);

      //then
      verify(hospitalRepository, times(1)).deleteById(id);
    }
  }

  @Nested
  @DisplayName("Test calls to calculate hospital capacity")
  class calculateHospitalCapacityTest {

    @Test
    @DisplayName("Should calculate capacity of a hospital.")
    void shouldCalculateCapacity() {
      //Given
      final UUID uuid = UUID.randomUUID();
      final Hospital hospital = new Hospital();
      final DepartmentCapacity departmentCapacity = new DepartmentCapacity("general");
      final HospitalCapacity hospitalCapacity = new HospitalCapacity("hos1");
      hospitalCapacity.setDepartmentCapacities(Collections.singletonList(departmentCapacity));
      final HospitalCapacityDto expected = new HospitalCapacityDto();
      expected.setDepartmentCapacities(Collections.singletonList(departmentCapacity));

      when(hospitalRepository.findById(uuid)).thenReturn(Optional.of(hospital));
      when(capacityService.calculateHospitalCapacity(hospital)).thenReturn(hospitalCapacity);
      when(hospitalCapacityDtoMapper.entityToDto(hospitalCapacity)).thenReturn(expected);

      //When
      HospitalCapacityDto actual = hospitalService.calculateCapacity(uuid);

      //Then
      assertEquals(expected, actual);
      verify(hospitalRepository, times(1)).findById(uuid);
      verify(capacityService, times(1)).calculateHospitalCapacity(hospital);
      verify(hospitalCapacityDtoMapper, times(1)).entityToDto(hospitalCapacity);
    }

    @Test
    @DisplayName("Should throw exception when no hospital found for provided id to calculate capacity.")
    void shouldThrowNoSuchHospitalExceptionWhenCalculateCapacity() {
      //Given
      final UUID uuid = UUID.randomUUID();
      final Hospital hospital = new Hospital();
      final DepartmentCapacity departmentCapacity = new DepartmentCapacity("general");
      final HospitalCapacity hospitalCapacity = new HospitalCapacity("hos1");
      hospitalCapacity.setDepartmentCapacities(Collections.singletonList(departmentCapacity));
      final HospitalCapacityDto expected = new HospitalCapacityDto();
      expected.setDepartmentCapacities(Collections.singletonList(departmentCapacity));

      when(hospitalRepository.findById(uuid)).thenReturn(Optional.empty());

      //When
      NoSuchHospitalException e = assertThrows(
          NoSuchHospitalException.class,
          () -> hospitalService.calculateCapacity(uuid));

      //Then
      assertEquals("Could not find hospital with ID " + uuid, e.getMessage());
      verify(hospitalRepository, times(1)).findById(uuid);
      verify(capacityService, times(0)).calculateHospitalCapacity(hospital);
      verify(hospitalCapacityDtoMapper, times(0)).entityToDto(hospitalCapacity);
    }
  }
}