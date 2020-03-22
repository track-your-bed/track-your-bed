package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;
import java.util.UUID;

public class NoSuchDepartmentException extends NoSuchElementException {

  public NoSuchDepartmentException(final UUID missingId) {
    super(format("Could not find department with ID %s", missingId));
  }

}
