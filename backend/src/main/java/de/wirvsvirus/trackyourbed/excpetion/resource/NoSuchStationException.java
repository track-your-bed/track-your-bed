package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;
import java.util.UUID;

public class NoSuchStationException extends NoSuchElementException {
  public NoSuchStationException(final UUID missingId) {
    super(format("Could not find station with ID %s", missingId));
  }
}
