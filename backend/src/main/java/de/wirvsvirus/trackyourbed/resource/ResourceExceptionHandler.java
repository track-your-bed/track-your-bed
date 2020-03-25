package de.wirvsvirus.trackyourbed.resource;

import de.wirvsvirus.trackyourbed.excpetion.dependency.BedMissingException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.DepartmentMissingException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.HospitalMissingException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.InvalidBedStateException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.InvalidBedTypeException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.InvalidWardTypeException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.InvalidDepartmentTypeException;
import de.wirvsvirus.trackyourbed.excpetion.dependency.WardMissingException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedStateException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchBedTypeException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchDepartmentException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchDepartmentTypeException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchHospitalException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchWardException;
import de.wirvsvirus.trackyourbed.excpetion.resource.NoSuchWardTypeException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(NoSuchBedException.class)
  public ResponseEntity<HTTPError> mapNoSuchBedExceptionToNotFoundResponse(
      final NoSuchBedException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.BED_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(httpError);
  }

  @ExceptionHandler(NoSuchBedStateException.class)
  public ResponseEntity<HTTPError> mapNoSuchBedStateExceptionToNotFoundResponse(
      final NoSuchBedStateException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.BED_STATE_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(httpError);
  }

  @ExceptionHandler(NoSuchBedTypeException.class)
  public ResponseEntity<HTTPError> mapNoSuchBedTypeExceptionToNotFoundResponse(
      final NoSuchBedTypeException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.BED_TYPE_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(httpError);
  }

  @ExceptionHandler(NoSuchDepartmentException.class)
  public ResponseEntity<HTTPError> mapNoSuchDepartmentExceptionToNotFoundResponse(
      final NoSuchDepartmentException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.DEPARTMENT_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(httpError);
  }

  @ExceptionHandler(NoSuchDepartmentTypeException.class)
  public ResponseEntity<HTTPError> mapNoSuchDepartmentTypeExceptionToNotFoundResponse(
      final NoSuchDepartmentTypeException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.DEPARTMENT_TYPE_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(httpError);
  }

  @ExceptionHandler(NoSuchHospitalException.class)
  public ResponseEntity<HTTPError> mapNoSuchHospitalExceptionToNotFoundResponse(
      final NoSuchHospitalException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.HOSPITAL_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(httpError);
  }

  @ExceptionHandler(NoSuchWardException.class)
  public ResponseEntity<HTTPError> mapNoSuchWardExceptionToNotFoundResponse(
      final NoSuchWardException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.WARD_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(httpError);
  }

  @ExceptionHandler(NoSuchWardTypeException.class)
  public ResponseEntity<HTTPError> mapNoSuchWardTypeExceptionToNotFoundResponse(
      final NoSuchWardTypeException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.WARD_TYPE_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(httpError);
  }

  @ExceptionHandler(DepartmentMissingException.class)
  public ResponseEntity<HTTPError> mapDepartmentMissingExceptionToBadRequestResponse(
      final DepartmentMissingException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.DEPARTMENT_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpError);
  }

  @ExceptionHandler(InvalidDepartmentTypeException.class)
  public ResponseEntity<HTTPError> mapInvalidDepartmentTypeExceptionToBadRequestResponse(
      final InvalidDepartmentTypeException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.DEPARTMENT_TYPE_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpError);
  }

  @ExceptionHandler(HospitalMissingException.class)
  public ResponseEntity<HTTPError> mapHospitalMissingExceptionToBadRequestResponse(
      final HospitalMissingException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.HOSPITAL_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpError);
  }

  @ExceptionHandler(BedMissingException.class)
  public ResponseEntity<HTTPError> mapBedMissingExceptionToBadRequestResponse(
      final BedMissingException e){
    final HTTPError httpError = new HTTPError(ErrorCode.BED_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpError);
  }

  @ExceptionHandler(InvalidBedStateException.class)
  public ResponseEntity<HTTPError> mapInvalidBedStateExceptionToBadRequestResponse(
      final InvalidBedStateException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.BED_STATE_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpError);
  }

  @ExceptionHandler(InvalidBedTypeException.class)
  public ResponseEntity<HTTPError> mapInvalidBedTypeExceptionToBadRequestResponse(
      final InvalidBedTypeException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.BED_TYPE_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpError);
  }

  @ExceptionHandler(InvalidWardTypeException.class)
  public ResponseEntity<HTTPError> mapInvalidWardTypeExceptionToBadRequestResponse(
      final InvalidWardTypeException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.WARD_TYPE_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpError);
  }

  @ExceptionHandler(WardMissingException.class)
  public ResponseEntity<HTTPError> mapWardMissingExceptionToBadRequestResponse(
      final WardMissingException e) {
    final HTTPError httpError = new HTTPError(ErrorCode.WARD_NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpError);
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<String> mapEmptyResultDataAccessExceptionToNotFoundResponse(
      final EmptyResultDataAccessException e) {
    return ResponseEntity.notFound().build();
  }

}
