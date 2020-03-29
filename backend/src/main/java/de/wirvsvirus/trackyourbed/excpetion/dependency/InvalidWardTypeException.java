package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;

public class InvalidWardTypeException extends IllegalStateException {

  public static final String MESSAGE_TEMPLATE = "There is no ward type called %s";

  public InvalidWardTypeException(final String missingName) {
    super(format(MESSAGE_TEMPLATE, missingName));
  }

}
