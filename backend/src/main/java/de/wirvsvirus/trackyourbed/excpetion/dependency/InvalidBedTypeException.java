package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;

public class InvalidBedTypeException extends IllegalStateException {

  public static final String MESSAGE_TEMPLATE = "There is no bed type called %s";

  public InvalidBedTypeException(final String missingName) {
    super(format(MESSAGE_TEMPLATE, missingName));
  }

}
