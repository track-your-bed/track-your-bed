package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;


import java.util.UUID;

public class DepartmentMissingException extends IllegalStateException {

  public static final String MESSAGE_TEMPLATE = "Could not find department with ID %s";

  public DepartmentMissingException(final UUID missingId) {
    super(format(MESSAGE_TEMPLATE, missingId));
  }

}
