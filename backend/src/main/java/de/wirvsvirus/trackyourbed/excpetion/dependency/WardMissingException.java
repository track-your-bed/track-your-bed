package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;


import java.util.UUID;

public class WardMissingException extends IllegalStateException {

  public WardMissingException(final UUID missingId) {
    super(format("Could not find ward with ID %s", missingId));
  }

}
