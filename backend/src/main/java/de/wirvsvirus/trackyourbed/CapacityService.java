package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.entity.Bed;
import de.wirvsvirus.trackyourbed.entity.Capacity;
import de.wirvsvirus.trackyourbed.entity.Department;
import de.wirvsvirus.trackyourbed.entity.DepartmentCapacity;
import de.wirvsvirus.trackyourbed.entity.FlatCapacity;
import de.wirvsvirus.trackyourbed.entity.Hospital;
import de.wirvsvirus.trackyourbed.entity.HospitalCapacity;
import de.wirvsvirus.trackyourbed.entity.Ward;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CapacityService {

  public HospitalCapacity calculateCapacity(final Hospital hospital) {
    final HospitalCapacity hospitalCapacity = new HospitalCapacity(hospital.getName());
    final HashMap<UUID, FlatCapacity> wardMap = new HashMap<>();
    final HashMap<UUID, FlatCapacity> departmentMap = new HashMap<>();

    calculateCapacity(hospital, hospitalCapacity, wardMap, departmentMap);

    return hospitalCapacity;
  }

  private void calculateCapacity(
      final Hospital hospital,
      final HospitalCapacity hospitalCapacity,
      final HashMap<UUID, FlatCapacity> wardMap,
      final HashMap<UUID, FlatCapacity> departmentMap) {
    for (final Department department : hospital.getDepartments()) {
      calculateCapacityForDepartment(hospitalCapacity, wardMap, departmentMap, department);
    }

    final List<FlatCapacity> departmentCapacities = new ArrayList<>(departmentMap.values());
    final List<FlatCapacity> wardCapacities = new ArrayList<>(wardMap.values());
    hospitalCapacity.setDepartmentCapacities(departmentCapacities);
    hospitalCapacity.setWardCapacities(wardCapacities);
  }

  private void calculateCapacityForDepartment(
      final HospitalCapacity hospitalCapacity,
      final HashMap<UUID, FlatCapacity> wardMap,
      final HashMap<UUID, FlatCapacity> departmentMap,
      final Department department) {
    final UUID departmentId = department.getId();
    if (departmentMap.get(departmentId) == null) {
      departmentMap.put(departmentId, new FlatCapacity(department.getName()));
    }
    for (final Ward ward : department.getWards()) {
      calculateCapacityForWard(hospitalCapacity, wardMap, departmentMap, departmentId, ward);
    }
  }

  private void calculateCapacityForWard(
      final HospitalCapacity hospitalCapacity,
      final HashMap<UUID, FlatCapacity> wardMap,
      final HashMap<UUID, FlatCapacity> departmentMap,
      final UUID departmentId,
      final Ward ward) {
    final UUID wardId = ward.getId();
    if (wardMap.get(wardId) == null) {
      wardMap.put(wardId, new FlatCapacity(ward.getName()));
    }
    final List<Bed> wardBeds = ward.getBeds();
    final int wardBedCount = wardBeds.size();
    Optional.ofNullable(hospitalCapacity).ifPresent(c -> c.getAll().addToMaxCapacity(wardBedCount));
    final FlatCapacity departmentCapacity;
    if (departmentMap != null) {
      departmentCapacity = departmentMap.get(departmentId);
    } else {
      departmentCapacity = null;
    }
    Optional.ofNullable(departmentCapacity)
        .ifPresent(d -> d.getAll().addToMaxCapacity(wardBedCount));
    final FlatCapacity wardCapactiy = wardMap.get(wardId);
    wardCapactiy.getAll().addToMaxCapacity(wardBedCount);

    final int freeBeds = (int) wardBeds.stream()
        .filter(w -> "free".equals(w.getBedState().getName()))
        .count();
    Optional.ofNullable(hospitalCapacity).ifPresent(h ->
        h.getAll().addToFreeCapacity(freeBeds)
    );
    Optional.ofNullable(departmentCapacity)
        .ifPresent(d -> d.getAll().addToFreeCapacity(freeBeds));
    wardCapactiy.getAll().addToFreeCapacity(freeBeds);

    filterByBedTypeNameAndAddToStatistics(wardBeds,
        "imc",
        hospitalCapacity == null ? null : hospitalCapacity.getImc(),
        departmentCapacity == null ? null : departmentCapacity.getImc(),
        wardCapactiy.getImc());

    filterByBedTypeNameAndAddToStatistics(wardBeds,
        "icu",
        hospitalCapacity == null ? null : hospitalCapacity.getIcu(),
        departmentCapacity == null ? null : departmentCapacity.getIcu(),
        wardCapactiy.getIcu());

    filterByBedTypeNameAndAddToStatistics(wardBeds,
        "covid",
        hospitalCapacity == null ? null : hospitalCapacity.getCovid(),
        departmentCapacity == null ? null : departmentCapacity.getCovid(),
        wardCapactiy.getCovid());


    filterByBedTypeNameAndAddToStatistics(wardBeds,
        "covid-icu",
        hospitalCapacity == null ? null : hospitalCapacity.getCovidIcu(),
        departmentCapacity == null ? null : departmentCapacity.getCovidIcu(),
        wardCapactiy.getCovidIcu());
  }

  private void filterByBedTypeNameAndAddToStatistics(
      final List<Bed> beds,
      final String bedTypeName,
      final Capacity... capacities) {
    final List<Bed> bedsOfType = beds.stream()
        .filter(bed -> bedTypeName.equals(bed.getBedType().getName()))
        .collect(Collectors.toList());
    final int totalCount = bedsOfType.size();
    final int freeCount = (int) bedsOfType.stream()
        .filter(bed -> "free".equals(bed.getBedState().getName()))
        .count();
    for (final Capacity capacity : capacities) {
      Optional.ofNullable(capacity).ifPresent(c -> {
        c.addToMaxCapacity(totalCount);
        c.addToFreeCapacity(freeCount);
      });
    }
  }

  public DepartmentCapacity calculateCapacity(final Department department) {
    final DepartmentCapacity departmentCapacity = new DepartmentCapacity(department.getName());
    final HashMap<UUID, FlatCapacity> wardMap = new HashMap<>();

    final HashMap<UUID, FlatCapacity> departmentCapacities= new HashMap<>();
    departmentCapacities.put(department.getId(), departmentCapacity);

    calculateCapacityForDepartment(null, wardMap, departmentCapacities, department);

    return departmentCapacity;
  }

  public FlatCapacity calculateCapacity(final Ward ward) {

    final HashMap<UUID, FlatCapacity> wardMap = new HashMap<>();
    final FlatCapacity wardCapacity = new FlatCapacity(ward.getName());
    wardMap.put(ward.getId(), wardCapacity);
    calculateCapacityForWard(null, wardMap, null, null, ward);

    return wardCapacity;
  }
}
