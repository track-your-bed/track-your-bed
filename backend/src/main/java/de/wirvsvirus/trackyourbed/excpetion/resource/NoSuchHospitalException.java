package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;
import java.util.UUID;

public class NoSuchHospitalException extends NoSuchElementException {
  public NoSuchHospitalException(final UUID missingId) {
    super(format("Could not find hospital with ID %s", missingId));
  }
}
