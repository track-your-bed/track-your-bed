package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;

public class NoSuchDepartmentTypeException extends NoSuchElementException {
  public NoSuchDepartmentTypeException(final String missingName) {
    super(format("There is no department type called %s", missingName));
  }
}
