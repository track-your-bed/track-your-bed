package de.wirvsvirus.trackyourbed.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department extends AbstractBaseEntity {

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "hospital_id")
  private Hospital hospital;

  @OneToMany(mappedBy = "department")
  private List<Ward> wards;

  @ManyToOne
  @JoinColumn(name = "department_type_name", referencedColumnName = "name")
  private DepartmentType departmentType;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Hospital getHospital() {
    return hospital;
  }

  public void setHospital(final Hospital hospital) {
    this.hospital = hospital;
  }

  public List<Ward> getWards() {
    return wards;
  }

  public void setWards(final List<Ward> wards) {
    this.wards = wards;
  }

  public DepartmentType getDepartmentType() {
    return departmentType;
  }

  public void setDepartmentType(final DepartmentType departmentType) {
    this.departmentType = departmentType;
  }
}
