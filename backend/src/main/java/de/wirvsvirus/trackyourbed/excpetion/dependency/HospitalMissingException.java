package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;


import java.util.UUID;

public class HospitalMissingException extends IllegalStateException {
  public HospitalMissingException(UUID missingId) {
    super(format("Could not find hospital with ID %s", missingId));
  }
}
