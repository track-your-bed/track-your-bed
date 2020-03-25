package de.wirvsvirus.trackyourbed.excpetion.dependency;

import static java.lang.String.format;


import java.util.UUID;

public class WardMissingException extends IllegalStateException {

  public static final String MESSAGE_TEMPLATE = "Could not find ward with ID %s";

  public WardMissingException(final UUID missingId) {
    super(format(MESSAGE_TEMPLATE, missingId));
  }

}
