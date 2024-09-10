package com.zervladpy.spring_security_module.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * GlobalExceptionHandler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = { ExpiredJwtException.class })
  public ResponseEntity<Object> handleApiRequestException(HttpServletRequest request, ExpiredJwtException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.I_AM_A_TEAPOT);
  }

  @ExceptionHandler(value = { BadCredentialsException.class })
  public ResponseEntity<Object> handleUsernameOrEmailNotFoundException(
      HttpServletRequest request,
       BadCredentialsException exception) {

    return new ResponseEntity<>(exception, HttpStatus.I_AM_A_TEAPOT);
  }

}
