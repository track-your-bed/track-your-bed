package de.wirvsvirus.trackyourbed.entity;

public class WardCapacity extends BedCapacity {
  private String name;

  public WardCapacity(final String name) {
    super();
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

}
