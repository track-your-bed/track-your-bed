package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;

public class InvalidBedStateException extends IllegalStateException {

  public static final String MESSAGE_TEMPLATE = "There is no bed state called %s";

  public InvalidBedStateException(final String missingName) {
    super(format(MESSAGE_TEMPLATE, missingName));
  }

}
