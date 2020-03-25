package de.wirvsvirus.trackyourbed.dto.response;

public class CapacityDto {
  private int maxCapacity;
  private int freeCapacity;

  public int getMaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCapacity(final int maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  public int getFreeCapacity() {
    return freeCapacity;
  }

  public void setFreeCapacity(final int freeCapacity) {
    this.freeCapacity = freeCapacity;
  }
}
