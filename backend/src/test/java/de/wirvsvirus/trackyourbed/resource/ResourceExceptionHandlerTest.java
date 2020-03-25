package de.wirvsvirus.trackyourbed.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;


import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedStateException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedTypeException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchDepartmentException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchDepartmentTypeException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchHospitalException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchWardException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchWardTypeException;
import java.util.UUID;
import org.glassfish.jersey.internal.Errors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ResourceExceptionHandlerTest {

  @Test
  @DisplayName("Should map NoSuchBedExceptions to NotFoundResponses.")
  void shouldMapNoSuchBedExceptionToNotFoundResponse() {
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

  @Test
  @DisplayName("Should map NoSuchBedStateExceptions to NotFoundResponses.")
  void mapNoSuchBedStateExceptionsToNotFoundResponse(){
    //GIVEN
    final String bedName = "name";
    final NoSuchBedStateException e = new NoSuchBedStateException(bedName);

    //WHEN
    final ResponseEntity<HTTPError> actual =
        new ResourceExceptionHandler().mapNoSuchBedStateExceptionToNotFoundResponse(e);

    //THEN
    assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    final HTTPError body = actual.getBody();
    assertNotNull(body);
    assertEquals(ErrorCode.BED_STATE_NOT_FOUND, body.getErrorCode());
    assertEquals(e.getMessage(), body.getErrorMessage());
  }

  @Test
  @DisplayName("Should map NoSuchBedTypeExceptions to NotFoundResponses.")
  void shouldMapNoSuchBedTypeExceptionToNotFoundResponse() {
    //GIVEN
    final String name = "name";
    final NoSuchBedTypeException e = new NoSuchBedTypeException(name);

    //WHEN
    final ResponseEntity<HTTPError> actual = new ResourceExceptionHandler().
        mapNoSuchBedTypeExceptionToNotFoundResponse(e);

    //THEN
    assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    final HTTPError body = actual.getBody();
    assertNotNull(body);
    assertEquals(ErrorCode.BED_TYPE_NOT_FOUND, body.getErrorCode());
    assertEquals(e.getMessage(), body.getErrorMessage());
  }

  @Test
  @DisplayName("Should map NoSuchDepartmentExceptions to NotFoundResponses.")
  void shouldMapNoSuchDepartmentExceptionToNotFoundResponses() {
    //GIVEN
    final UUID id = UUID.randomUUID();
    final NoSuchDepartmentException e = new NoSuchDepartmentException(id);

    //WHEN
    ResponseEntity<HTTPError> actual =
        new ResourceExceptionHandler().mapNoSuchDepartmentExceptionToNotFoundResponse(e);

    //THEN
    assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    HTTPError body = actual.getBody();
    assertNotNull(body);
    assertEquals(ErrorCode.DEPARTMENT_NOT_FOUND, body.getErrorCode());
    assertEquals(e.getMessage(), body.getErrorMessage());
  }

  @Test
  @DisplayName("Should map NoSuchDepartmentTypeException to NotFoundResponses.")
  void shouldMapNoSuchDepartmentTypeExceptionToNotFoundResponse() {
    //GIVEN
    final String name = "name";
    final NoSuchDepartmentTypeException e =
        new NoSuchDepartmentTypeException(name);

    //WHEN
    ResponseEntity<HTTPError> actual =
        new ResourceExceptionHandler().mapNoSuchDepartmentTypeExceptionToNotFoundResponse(e);

    //THEN
    assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    HTTPError body = actual.getBody();
    assertNotNull(body);
    assertEquals(ErrorCode.DEPARTMENT_TYPE_NOT_FOUND, body.getErrorCode());
    assertEquals(e.getMessage(), body.getErrorMessage());
  }

  @Test
  @DisplayName("Should map NoSuchHospitalExceptions to NotFoundResponses.")
  void shouldMapNoSuchHospitalExceptionsToNotFoundResponses() {
    //GIVEN
    final UUID id = UUID.randomUUID();
    final NoSuchHospitalException e = new NoSuchHospitalException(id);

    //WHEN
    ResponseEntity<HTTPError> actual =
        new ResourceExceptionHandler().mapNoSuchHospitalExceptionToNotFoundResponse(e);

    //THEN
    assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    HTTPError body = actual.getBody();
    assertNotNull(body);
    assertEquals(ErrorCode.HOSPITAL_NOT_FOUND, body.getErrorCode());
    assertEquals(e.getMessage(), body.getErrorMessage());
  }

  @Test
  @DisplayName("Should map NoSuchWardExceptions to NotFoundResponses.")
  void shouldMapNoSuchWardExceptionToNotFoundResponse() {
    //GIVEN
    final UUID id = UUID.randomUUID();
    final NoSuchWardException e = new NoSuchWardException(id);

    //WHEN
    final ResponseEntity<HTTPError> actual =
        new ResourceExceptionHandler().mapNoSuchWardExceptionToNotFoundResponse(e);

    //THEN
    assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    HTTPError body = actual.getBody();
    assertNotNull(body);
    assertEquals(ErrorCode.WARD_NOT_FOUND, body.getErrorCode());
    assertEquals(e.getMessage(), body.getErrorMessage());
  }

  @Test
  @DisplayName("Should map NoSuchWardTypeExceptions to NotFoundResponses.")
  void shouldMapNoSuchWardTypeExceptionToNotFoundResponse() {
    //GIVEN
    final String name = "name";
    final NoSuchWardTypeException e = new NoSuchWardTypeException(name);

    //WHEN
    final ResponseEntity<HTTPError> actual =
        new ResourceExceptionHandler().mapNoSuchWardTypeExceptionToNotFoundResponse(e);

    //THEN
    assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    HTTPError body = actual.getBody();
    assertNotNull(body);
    assertEquals(ErrorCode.WARD_TYPE_NOT_FOUND, body.getErrorCode());
    assertEquals(e.getMessage(), body.getErrorMessage());
  }

}