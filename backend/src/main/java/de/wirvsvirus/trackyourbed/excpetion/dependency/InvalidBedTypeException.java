package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;

public class InvalidBedTypeException extends IllegalStateException {

  public InvalidBedTypeException(final String missingName) {
    super(format("There is no bed type called %s", missingName));
  }

}
