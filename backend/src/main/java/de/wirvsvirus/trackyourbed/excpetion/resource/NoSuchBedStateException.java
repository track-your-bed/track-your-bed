package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;

public class NoSuchBedStateException extends NoSuchElementException {

  public static final String MESSAGE_TEMPLATE = "There is no bed state called %s";

  public NoSuchBedStateException(final String missingName) {
    super(format(MESSAGE_TEMPLATE, missingName));
  }

}
