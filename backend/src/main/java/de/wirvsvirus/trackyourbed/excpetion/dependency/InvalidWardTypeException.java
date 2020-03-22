package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;

public class InvalidWardTypeException extends IllegalStateException {
  public InvalidWardTypeException(final String missingName) {
    super(format("There is no ward type called %s", missingName));
  }
}
