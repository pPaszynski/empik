package com.paszynski.empik.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(HttpClientErrorException.NotFound.class)
  public ResponseEntity<?> handleNotFoundException(HttpClientErrorException.NotFound exception) {
    return createResponse(HttpStatus.NOT_FOUND, exception.toString());
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception) {
    return createResponse(HttpStatus.NOT_FOUND, exception.getMessage());
  }

  @ExceptionHandler(ClientException.class)
  public ResponseEntity<?> handleClientException(ClientException exception) {
    return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public Object handleGlobalException() {
    return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Critical problem");
  }

  private ResponseEntity<?> createResponse(HttpStatus status, String message) {
    ErrorDetails errorDetails = new ErrorDetails(status.value(), message);
    return new ResponseEntity<>(errorDetails, status);
  }
}
