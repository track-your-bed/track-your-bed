package de.wirvsvirus.trackyourbed.dto.response;

import de.wirvsvirus.trackyourbed.entity.Capacity;

public class WardCapacityDto {
  private String name;
  private Capacity all;
  private Capacity normal;
  private Capacity imc;
  private Capacity icu;
  private Capacity covid;
  private Capacity covidIcu;

  public WardCapacityDto() {
    all = new Capacity();
    normal = new Capacity();
    imc = new Capacity();
    icu = new Capacity();
    covid = new Capacity();
    covidIcu = new Capacity();
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Capacity getAll() {
    return all;
  }

  public void setAll(final Capacity all) {
    this.all = all;
  }

  public Capacity getNormal() {
    return normal;
  }

  public void setNormal(final Capacity normal) {
    this.normal = normal;
  }

  public Capacity getImc() {
    return imc;
  }

  public void setImc(final Capacity imc) {
    this.imc = imc;
  }

  public Capacity getIcu() {
    return icu;
  }

  public void setIcu(final Capacity icu) {
    this.icu = icu;
  }

  public Capacity getCovid() {
    return covid;
  }

  public void setCovid(final Capacity covid) {
    this.covid = covid;
  }

  public Capacity getCovidIcu() {
    return covidIcu;
  }

  public void setCovidIcu(final Capacity covidIcu) {
    this.covidIcu = covidIcu;
  }
}
