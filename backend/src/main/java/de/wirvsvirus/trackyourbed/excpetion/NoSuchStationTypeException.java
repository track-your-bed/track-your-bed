package de.wirvsvirus.trackyourbed.excpetion;

import java.util.NoSuchElementException;

public class NoSuchStationTypeException extends NoSuchElementException {
  public NoSuchStationTypeException(String missingName) {
    super("There is no station type called " + missingName);
  }
}
