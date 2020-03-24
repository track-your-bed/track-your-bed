package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;


import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ResourceExceptionHandlerTest {

  @Test
  void shouldMapNoSuchBedExceptionToNotFoundException() {
    // GIVEN
    final UUID bedId = UUID.randomUUID();
    final NoSuchBedException e = new NoSuchBedException(bedId);

    // WHEN
    final ResponseEntity<HTTPError> actual =
        new ResourceExceptionHandler().mapNoSuchBedExceptionToNotFoundResponse(e);

    // THEN
    assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    final HTTPError body = actual.getBody();
    assertNotNull(body);
    assertSame(ErrorCode.BED_NOT_FOUND, body.getErrorCode());
    assertSame(e.getMessage(), body.getErrorMessage());
  }

}