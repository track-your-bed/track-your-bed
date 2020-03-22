package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.entity.Bed;
import de.wirvsvirus.trackyourbed.entity.Capacity;
import de.wirvsvirus.trackyourbed.entity.Department;
import de.wirvsvirus.trackyourbed.entity.DepartmentCapacity;
import de.wirvsvirus.trackyourbed.entity.Hospital;
import de.wirvsvirus.trackyourbed.entity.HospitalCapacity;
import de.wirvsvirus.trackyourbed.entity.Ward;
import de.wirvsvirus.trackyourbed.entity.WardCapacity;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CapacityService {

  private static final String NORMAL = "normal";
  private static final String IMC = "imc";
  private static final String ICU = "icu";
  private static final String COVID = "covid";
  private static final String COVID_ICU = "covid-icu";

  public HospitalCapacity calculateHospitalCapacity(final Hospital hospital) {
    final List<DepartmentCapacity> departmentCapacities = hospital.getDepartments().stream()
        .map(this::calculateDepartmentCapacity)
        .collect(Collectors.toList());
    final HospitalCapacity hospitalCapacity = new HospitalCapacity(hospital.getName());
    hospitalCapacity.add(departmentCapacities);
    hospitalCapacity.setDepartmentCapacities(departmentCapacities);
    return hospitalCapacity;
  }

  public DepartmentCapacity calculateDepartmentCapacity(final Department department) {
    final List<WardCapacity> wardCapacities = department.getWards().stream()
        .map(this::calculateWardCapacity)
        .collect(Collectors.toList());

    final DepartmentCapacity departmentCapacity = new DepartmentCapacity(department.getName());
    departmentCapacity.add(wardCapacities);
    departmentCapacity.setWardCapacities(wardCapacities);
    return departmentCapacity;
  }

  public WardCapacity calculateWardCapacity(final Ward ward) {
    final WardCapacity wardCapacity = new WardCapacity(ward.getName());
    final HashMap<String, Capacity> capacities = new HashMap<>();
    List.of(NORMAL, IMC, ICU, COVID, COVID_ICU).forEach(bedType -> {
      final Capacity capacity = calculateWardCapacityByBedType(ward, bedType);
      capacities.put(bedType, capacity);
    });

    wardCapacity.getAll().addToMaxCapacity(ward.getBeds().size());
    wardCapacity.getAll().addToFreeCapacity((int) ward.getBeds().stream()
        .filter(b -> "free".equals(b.getBedState().getName()))
        .count());

    wardCapacity.getNormal().addToMaxCapacity(capacities.get(NORMAL).getMaxCapacity());
    wardCapacity.getNormal().addToFreeCapacity(capacities.get(NORMAL).getFreeCapacity());

    wardCapacity.getImc().addToMaxCapacity(capacities.get(IMC).getMaxCapacity());
    wardCapacity.getImc().addToFreeCapacity(capacities.get(IMC).getFreeCapacity());

    wardCapacity.getIcu().addToMaxCapacity(capacities.get(ICU).getMaxCapacity());
    wardCapacity.getIcu().addToFreeCapacity(capacities.get(ICU).getFreeCapacity());

    wardCapacity.getCovid().addToMaxCapacity(capacities.get(COVID).getMaxCapacity());
    wardCapacity.getCovid().addToFreeCapacity(capacities.get(COVID).getFreeCapacity());

    wardCapacity.getCovidIcu().addToMaxCapacity(capacities.get(COVID_ICU).getMaxCapacity());
    wardCapacity.getCovidIcu().addToFreeCapacity(capacities.get(COVID_ICU).getFreeCapacity());
    return wardCapacity;
  }

  private Capacity calculateWardCapacityByBedType(
      final Ward ward,
      final String bedTypeName) {
    final List<Bed> bedsOfType = ward.getBeds()
        .stream()
        .filter(bed -> bedTypeName.equals(bed.getBedType().getName()))
        .collect(Collectors.toList());
    final int totalCount = bedsOfType.size();
    final int freeCount = (int) bedsOfType.stream()
        .filter(bed -> "free".equals(bed.getBedState().getName()))
        .count();
    return new Capacity()
        .addToMaxCapacity(totalCount)
        .addToFreeCapacity(freeCount);
  }
}
