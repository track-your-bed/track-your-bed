package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;

public class InvalidStationTypeException extends IllegalStateException {
  public InvalidStationTypeException(String missingName) {
    super(format("There is no station type called %s", missingName));
  }
}
