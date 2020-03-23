package de.wirvsvirus.trackyourbed.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ward")
public class Ward extends AbstractBaseEntity<Ward> {

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;

  @ManyToOne
  @JoinColumn(name = "ward_type_name", referencedColumnName = "name")
  private WardType wardType;

  @OneToMany(mappedBy = "ward")
  private List<Bed> beds;

  public String getName() {
    return name;
  }

  public Ward setName(final String name) {
    this.name = name;
    return this;
  }

  public Department getDepartment() {
    return department;
  }

  public Ward setDepartment(final Department department) {
    this.department = department;
    return this;
  }

  public WardType getWardType() {
    return wardType;
  }

  public Ward setWardType(final WardType wardType) {
    this.wardType = wardType;
    return this;
  }

  public List<Bed> getBeds() {
    return beds;
  }

  public Ward setBeds(final List<Bed> beds) {
    this.beds = beds;
    return this;
  }
}
