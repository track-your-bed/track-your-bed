package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;

public class NoSuchStationTypeException extends NoSuchElementException {
  public NoSuchStationTypeException(final String missingName) {
    super(format("There is no station type called %s", missingName));
  }
}
