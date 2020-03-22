package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;
import java.util.UUID;

public class NoSuchWardException extends NoSuchElementException {
  public NoSuchWardException(final UUID missingId) {
    super(format("Could not find ward with ID %s", missingId));
  }
}
