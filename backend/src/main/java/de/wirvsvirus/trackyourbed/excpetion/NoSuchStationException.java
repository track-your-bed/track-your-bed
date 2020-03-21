package de.wirvsvirus.trackyourbed.excpetion;

import java.util.NoSuchElementException;
import java.util.UUID;

public class NoSuchStationException extends NoSuchElementException {
  public NoSuchStationException(UUID missingId) {
    super("Could not find station with ID " + missingId);
  }
}
