package de.wirvsvirus.trackyourbed.entity;

import java.util.Collection;

public class WardCapacity {
  private String name;
  private Capacity all;
  private Capacity normal;
  private Capacity imc;
  private Capacity icu;
  private Capacity covid;
  private Capacity covidIcu;

  public WardCapacity(final String name) {
    this.name = name;
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

  public void add(final WardCapacity that) {
    this.getAll().addToMaxCapacity(that.getAll().getMaxCapacity());
    this.getAll().addToFreeCapacity(that.getAll().getFreeCapacity());

    this.getNormal().addToMaxCapacity(that.getNormal().getMaxCapacity());
    this.getNormal().addToFreeCapacity(that.getNormal().getFreeCapacity());

    this.getImc().addToMaxCapacity(that.getImc().getMaxCapacity());
    this.getImc().addToFreeCapacity(that.getImc().getFreeCapacity());

    this.getIcu().addToMaxCapacity(that.getIcu().getMaxCapacity());
    this.getIcu().addToFreeCapacity(that.getIcu().getFreeCapacity());

    this.getCovid().addToMaxCapacity(that.getCovid().getMaxCapacity());
    this.getCovid().addToFreeCapacity(that.getCovid().getFreeCapacity());

    this.getCovidIcu().addToMaxCapacity(that.getCovidIcu().getMaxCapacity());
    this.getCovidIcu().addToFreeCapacity(that.getCovidIcu().getFreeCapacity());
  }

  public void add(final Collection<? extends WardCapacity> those) {
    those.forEach(this::add);
  }

}
