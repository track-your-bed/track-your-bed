package de.wirvsvirus.trackyourbed.entity;

public class FlatCapacity {
  private String name;
  private Capacity all;
  private Capacity imc;
  private Capacity icu;
  private Capacity covid;
  private Capacity covidIcu;

  public FlatCapacity(final String name) {
    this.name = name;
    all = new Capacity();
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
