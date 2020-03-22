package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;


import java.util.UUID;

public class StationMissingException extends IllegalStateException {
  public StationMissingException(UUID missingId) {
    super(format("Could not find station with ID %s", missingId));
  }
}
