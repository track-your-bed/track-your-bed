package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;

public class NoSuchWardTypeException extends NoSuchElementException {

  public NoSuchWardTypeException(final String missingName) {
    super(format("There is no ward type called %s", missingName));
  }

}
