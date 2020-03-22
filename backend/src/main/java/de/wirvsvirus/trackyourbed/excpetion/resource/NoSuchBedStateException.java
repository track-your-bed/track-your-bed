package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;

public class NoSuchBedStateException extends NoSuchElementException {
  public NoSuchBedStateException(final String missingName) {
    super(format("There is no bed state called %s", missingName));
  }
}
