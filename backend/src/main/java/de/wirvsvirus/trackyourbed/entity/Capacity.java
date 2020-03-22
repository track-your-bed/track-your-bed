package de.wirvsvirus.trackyourbed.entity;

public class Capacity {
  private int maxCapacity;
  private int freeCapacity;

  public int getMaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCapacity(final int maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  public void addToMaxCapacity(final int delta) {
    maxCapacity += delta;
  }

  public int getFreeCapacity() {
    return freeCapacity;
  }

  public void addToFreeCapacity(final int delta) {
    freeCapacity += delta;
  }

  public void setFreeCapacity(final int freeCapacity) {
    this.freeCapacity = freeCapacity;
  }
}
