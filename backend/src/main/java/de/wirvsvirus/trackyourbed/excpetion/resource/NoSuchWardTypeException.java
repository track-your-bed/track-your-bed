package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;

public class NoSuchWardTypeException extends NoSuchElementException {

  public static final String MESSAGE_TEMPLATE = "There is no ward type called %s";

  public NoSuchWardTypeException(final String missingName) {
    super(format(MESSAGE_TEMPLATE, missingName));
  }

}
