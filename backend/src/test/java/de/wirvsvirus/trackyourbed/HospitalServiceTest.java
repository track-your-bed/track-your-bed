package de.wirvsvirus.trackyourbed;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Tests for HospitalService")
@ExtendWith(MockitoExtension.class)
class HospitalServiceTest {

  @Mock
  private CreateNewHospitalMapper createNewHospitalMapper;

  @Mock
  private HospitalRepository hospitalRepository;

  @Mock
  private HospitalDtoMapper hospitalDtoMapper;

  @Mock
  private CapacityService capacityService;

  @Mock
  private HospitalCapacityDtoMapper hospitalCapacityDtoMapper;

  private HospitalService hospitalService;

  @BeforeEach
  private void setUp() {
    this.hospitalService = new HospitalService(
        hospitalRepository,
        createNewHospitalMapper,
        hospitalDtoMapper,
        capacityService,
        hospitalCapacityDtoMapper);
  }

  @Test
  @DisplayName("Should call dependencies and return expected result when called.")
  void shouldCreateNewHospital() {
    // GIVEN
    final String name = "hospital";
    final int maxCapacity = 23;
    final String lon = "11.11";
    final String lan = "12.11";
    final CreateNewHospital request = new CreateNewHospital()
        .setMaxCapacity(maxCapacity)
        .setLon(lon)
        .setLat(lan)
        .setName(name);
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

  @Test
  void shouldDeleteHospital() {
    //Given
    doNothing().when(hospitalRepository).deleteById(any(UUID.class));

    //When
    hospitalService.deleteHospital(UUID.randomUUID());

    //then
    verify(hospitalRepository, times(1)).deleteById(any(UUID.class));
  }

  @Test
  void shouldGetHospitalById() {
    //Given
    final Hospital hospital = getMockHospital();
    final HospitalDto expected = new HospitalDto();
    UUID hospitalId = UUID.randomUUID();

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
  void shouldThrowNoSuchHospitalExceptionWhenGetHospitalById() {
    //Given
    final Hospital hospital = getMockHospital();
    UUID hospitalId = UUID.randomUUID();

    when(hospitalRepository.findById(hospitalId)).thenReturn(Optional.empty());

    //When
    try {
      hospitalService.getHospitalById(hospitalId);
    } catch (NoSuchHospitalException ex) {
      assertEquals("Could not find hospital with ID " + hospitalId, ex.getMessage());
    }

    //then
    verify(hospitalRepository, times(1)).findById(hospitalId);
    verify(hospitalDtoMapper, times(0)).entityToDto(hospital);
  }

  @Test
  void shouldGetAllHospitals() {
    //Given
    final Hospital hospital1 = new Hospital();
    hospital1.setName("hos1");
    hospital1.setMaxCapacity(1);
    final Hospital hospital2 = new Hospital();
    hospital2.setName("hos2");
    hospital2.setMaxCapacity(2);

    List<Hospital> hospitals = Arrays.asList(hospital1, hospital2);

    when(hospitalRepository.findAll()).thenReturn(hospitals);
    when(hospitalDtoMapper.entityToDto(any(Hospital.class))).thenAnswer(arguments -> {
          HospitalDto hospitalDto = new HospitalDto();
          hospitalDto.setName(arguments.getArgument(0, Hospital.class).getName());
          return hospitalDto;
        });

    //when
    Collection<HospitalDto> actual = hospitalService.getAllHospitals();

    //then
    assertEquals(2, actual.size());
    assertThat(
        actual.stream().map(HospitalDto::getName).collect(Collectors.toList()),
        containsInAnyOrder("hos1", "hos2")
    );

    verify(hospitalRepository).findAll();
    verify(hospitalDtoMapper).entityToDto(argThat(h -> Objects.equals("hos1", h.getName())));
    verify(hospitalDtoMapper).entityToDto(argThat(h -> Objects.equals("hos2", h.getName())));
  }

  @Test
  void shouldUpdateHospital() {
    //Given
    final UUID uuid = UUID.randomUUID();
    final UpdateHospital updateHospital = new UpdateHospital();
    updateHospital.setLat("11.11");
    updateHospital.setName("newHos");
    updateHospital.setLon("12.1");
    updateHospital.setMaxCapacity(12);
    final Hospital toBeUpdated = getMockHospital();

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
    try {
      hospitalService.updateHospital(uuid, updateHospital);
    } catch (NoSuchHospitalException ex) {
      assertEquals("Could not find hospital with ID " + uuid, ex.getMessage());
    }

    //Then
    verify(hospitalRepository, times(1)).findById(uuid);
    verify(hospitalDtoMapper, times(0)).entityToDto(any(Hospital.class));
  }

  @Test
  void deleteHospitalById() {
    //Given
    doNothing().when(hospitalRepository).deleteById(any(UUID.class));

    //When
    hospitalService.deleteHospitalById(UUID.randomUUID());

    //then
    verify(hospitalRepository, times(1)).deleteById(any(UUID.class));
  }

  @Test
  void shouldCalculateCapacity() {
    //Given
    UUID uuid = UUID.randomUUID();
    final Hospital hospital = getMockHospital();
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
  void shouldThrowNoServiceExcpetionWhenCalculateCapacity() {
    //Given
    UUID uuid = UUID.randomUUID();
    final Hospital hospital = getMockHospital();
    final DepartmentCapacity departmentCapacity = new DepartmentCapacity("general");
    final HospitalCapacity hospitalCapacity = new HospitalCapacity("hos1");
    hospitalCapacity.setDepartmentCapacities(Collections.singletonList(departmentCapacity));
    final HospitalCapacityDto expected = new HospitalCapacityDto();
    expected.setDepartmentCapacities(Collections.singletonList(departmentCapacity));

    when(hospitalRepository.findById(uuid)).thenReturn(Optional.empty());

    //When
    try {
      hospitalService.calculateCapacity(uuid);
    } catch (NoSuchHospitalException ex) {
      assertEquals("Could not find hospital with ID " + uuid, ex.getMessage());
    }

    //Then
    verify(hospitalRepository, times(1)).findById(uuid);
    verify(capacityService, times(0)).calculateHospitalCapacity(hospital);
    verify(hospitalCapacityDtoMapper, times(0)).entityToDto(hospitalCapacity);
  }

  private Hospital getMockHospital() {
    final Hospital hospital = new Hospital();
    hospital.setName("12");
    return hospital;
  }

}