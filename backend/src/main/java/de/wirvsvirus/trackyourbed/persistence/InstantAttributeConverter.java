package de.wirvsvirus.trackyourbed.persistence;

import java.sql.Timestamp;
import java.time.Instant;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Performs database column representation conversion between {@link Instant} and {@link Timestamp}.
 */
@Converter(autoApply = true)
public class InstantAttributeConverter implements AttributeConverter<Instant, Long> {

  /**
   * Converts {@link Instant} (entity field) to {@link Timestamp} (DB column).
   *
   * @param instant
   *         {@link Instant} (entity field).
   *
   * @return {@link Timestamp} (DB column).
   */
  @Override
  public Long convertToDatabaseColumn(final Instant instant) {
    return instant == null ? null : instant.toEpochMilli();
  }

  /**
   * Converts {@link Timestamp} (DB column) to {@link Instant} (entity field).
   *
   * @param timestamp
   *         {@link Timestamp} (DB column).
   *
   * @return {@link Instant} (entity field).
   */
  @Override
  public Instant convertToEntityAttribute(final Long timestamp) {
    return timestamp == null ? null : Instant.ofEpochMilli(timestamp);
  }

}
