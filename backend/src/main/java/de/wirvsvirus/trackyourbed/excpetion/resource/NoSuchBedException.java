package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;
import java.util.UUID;

public class NoSuchBedException extends NoSuchElementException {
  public NoSuchBedException(final UUID missingId) {
    super(format("There is no bed with ID %s", missingId));
  }
}
