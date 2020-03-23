package de.wirvsvirus.trackyourbed.entity;

import java.util.Collection;
import java.util.Objects;

public class BedCapacity {

  private static final String FREE_STATE = "free";
  private static final String NORMAL_TYPE = "normal";
  private static final String IMC_TYPE = "imc";
  private static final String ICU_TYPE = "icu";
  private static final String COVID_TYPE = "covid";
  private static final String COVID_ICU_TYPE = "covid-icu";

  private final Capacity all;
  private final Capacity normal;
  private final Capacity imc;
  private final Capacity icu;
  private final Capacity covid;
  private final Capacity covidIcu;

  public BedCapacity() {
    all = new Capacity();
    normal = new Capacity();
    imc = new Capacity();
    icu = new Capacity();
    covid = new Capacity();
    covidIcu = new Capacity();
  }

  public BedCapacity(final Bed bed) {
    all = mapBedToCapacityByFreeState(bed);
    normal = mapBedToCapacityByType(bed, NORMAL_TYPE);
    imc = mapBedToCapacityByType(bed, IMC_TYPE);
    icu = mapBedToCapacityByType(bed, ICU_TYPE);
    covid = mapBedToCapacityByType(bed, COVID_TYPE);
    covidIcu = mapBedToCapacityByType(bed, COVID_ICU_TYPE);
  }

  private Capacity mapBedToCapacityByFreeState(final Bed bed) {
    final Capacity capacity = new Capacity().addToMaxCapacity(1);
    if (Objects.equals(FREE_STATE, bed.getBedState().getName())) {
      capacity.addToFreeCapacity(1);
    }
    return capacity;
  }

  private Capacity mapBedToCapacityByType(final Bed bed, final String bedType) {
    if (Objects.equals(bedType, bed.getBedType().getName())) {
      return mapBedToCapacityByFreeState(bed);
    }
    return new Capacity();
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
    this.getAll().add(that.getAll());
    this.getNormal().add(that.getNormal());
    this.getImc().add(that.getImc());
    this.getIcu().add(that.getIcu());
    this.getCovid().add(that.getCovid());
    this.getCovidIcu().add(that.getCovidIcu());
  }

  public void add(final Collection<? extends BedCapacity> those) {
    those.forEach(this::add);
  }
}
