package de.wirvsvirus.trackyourbed.excpetion;

import java.util.NoSuchElementException;
import java.util.UUID;

public class NoSuchHospitalException extends NoSuchElementException {
  public NoSuchHospitalException(UUID missingId) {
    super("Could not find hospital with ID " + missingId);
  }
}
