package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;

public class InvalidBedStateException extends IllegalStateException {
  public InvalidBedStateException(final String missingName) {
    super(format("There is no bed state called %s", missingName));
  }
}
