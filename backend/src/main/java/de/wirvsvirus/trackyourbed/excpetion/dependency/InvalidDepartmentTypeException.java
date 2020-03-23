package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;

public class InvalidDepartmentTypeException extends IllegalStateException {

  public InvalidDepartmentTypeException(final String missingName) {
    super(format("There is no department type called %s", missingName));
  }

}
