package de.wirvsvirus.trackyourbed.resource;

import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Void> mapNoSuchElementExceptionToNotFoundResponse(
      final NoSuchElementException e) {
    return ResponseEntity.notFound().build();
  }
}