package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;


import java.util.UUID;

public class BedMissingException extends IllegalStateException{

  public BedMissingException(final UUID missingId) {
    super(format("Could not find department with ID %s", missingId));
  }
}
