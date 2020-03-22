package de.wirvsvirus.trackyourbed.entity;

public class Capacity {
  private int maxCapacity;
  private int freeCapacity;

  public Capacity() {
    maxCapacity = 0;
    freeCapacity = 0;
  }

  public Capacity(Bed bed) {
    maxCapacity = 1;
    freeCapacity = "free".equals(bed.getBedState().getName()) ? 1 : 0;
  }

  public int getMaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCapacity(final int maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  public Capacity addToMaxCapacity(final int delta) {
    maxCapacity += delta;
    return this;
  }

  public int getFreeCapacity() {
    return freeCapacity;
  }

  public Capacity addToFreeCapacity(final int delta) {
    freeCapacity += delta;
    return this;
  }

  public void setFreeCapacity(final int freeCapacity) {
    this.freeCapacity = freeCapacity;
  }
}
