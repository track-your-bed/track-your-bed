package de.wirvsvirus.trackyourbed.excpetion.resource;

import static java.lang.String.format;


import java.util.NoSuchElementException;
import java.util.UUID;

public class NoSuchWardException extends NoSuchElementException {

  public static final String MESSAGE_TEMPLATE = "Could not find ward with ID %s";

  public NoSuchWardException(final UUID missingId) {
    super(format(MESSAGE_TEMPLATE, missingId));
  }

}
