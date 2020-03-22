package de.wirvsvirus.trackyourbed.entity;

import java.util.Collection;

public class BedCapacity {

  private static final String NORMAL = "normal";
  private static final String IMC = "imc";
  private static final String ICU = "icu";
  private static final String COVID = "covid";
  private static final String COVID_ICU = "covid-icu";

  private Capacity all;
  private Capacity normal;
  private Capacity imc;
  private Capacity icu;
  private Capacity covid;
  private Capacity covidIcu;

  public BedCapacity() {
    all = new Capacity();
    normal = new Capacity();
    imc = new Capacity();
    icu = new Capacity();
    covid = new Capacity();
    covidIcu = new Capacity();
  }

  public BedCapacity(final Bed bed) {
    all = new Capacity(bed);
    normal = NORMAL.equals(bed.getBedType().getName()) ? new Capacity(bed) : new Capacity();
    imc = IMC.equals(bed.getBedType().getName()) ? new Capacity(bed) : new Capacity();
    icu = ICU.equals(bed.getBedType().getName()) ? new Capacity(bed) : new Capacity();
    covid = COVID.equals(bed.getBedType().getName()) ? new Capacity(bed) : new Capacity();
    covidIcu = COVID_ICU.equals(bed.getBedType().getName()) ? new Capacity(bed) : new Capacity();
  }

  public Capacity getAll() {
    return all;
  }

  public Capacity getNormal() {
    return normal;
  }

  public Capacity getImc() {
    return imc;
  }

  public Capacity getIcu() {
    return icu;
  }

  public Capacity getCovid() {
    return covid;
  }

  public Capacity getCovidIcu() {
    return covidIcu;
  }

  public void add(final BedCapacity that) {
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

  public void add(final Collection<? extends BedCapacity> those) {
    those.forEach(this::add);
  }

}
