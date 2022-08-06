package com.batuhan.youcontribute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DuplicatedRepositoryException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ErrorResponse handleDuplicatedRepositoryException(DuplicatedRepositoryException exception, HttpServletRequest request) {
    return new ErrorResponse(exception.getMessage());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponse handleDuplicatedRepositoryException(EntityNotFoundException exception, HttpServletRequest request) {
    return new ErrorResponse(exception.getMessage());
  }
}
