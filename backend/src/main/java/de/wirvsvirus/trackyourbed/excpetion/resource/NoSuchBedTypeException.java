package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;

public class NoSuchBedTypeException extends NoSuchElementException {

  public NoSuchBedTypeException(final String missingName) {
    super(format("There is no bed type called %s", missingName));
  }

}
