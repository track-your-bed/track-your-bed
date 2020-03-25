package de.wirvsvirus.trackyourbed;

import de.wirvsvirus.trackyourbed.entity.BedCapacity;
import de.wirvsvirus.trackyourbed.entity.Department;
import de.wirvsvirus.trackyourbed.entity.DepartmentCapacity;
import de.wirvsvirus.trackyourbed.entity.Hospital;
import de.wirvsvirus.trackyourbed.entity.HospitalCapacity;
import de.wirvsvirus.trackyourbed.entity.Ward;
import de.wirvsvirus.trackyourbed.entity.WardCapacity;
import org.springframework.stereotype.Service;

@Service
public class CapacityService {

  public HospitalCapacity calculateHospitalCapacity(final Hospital hospital) {
    return hospital.getDepartments().stream()
        .map(this::calculateDepartmentCapacity)
        .collect(() -> new HospitalCapacity(hospital.getName()), HospitalCapacity::add, HospitalCapacity::add);
  }

  public DepartmentCapacity calculateDepartmentCapacity(final Department department) {
    return department.getWards().stream()
        .map(this::calculateWardCapacity)
        .collect(() -> new DepartmentCapacity(department.getName()), DepartmentCapacity::add, DepartmentCapacity::add);
  }

  public WardCapacity calculateWardCapacity(final Ward ward) {
    return ward.getBeds().stream()
        .map(BedCapacity::new)
        .collect(() -> new WardCapacity(ward.getName()), WardCapacity::add, WardCapacity::add);
  }
}
