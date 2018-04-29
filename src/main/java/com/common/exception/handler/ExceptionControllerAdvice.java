package com.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.common.dto.ErrorResponseDto;
import com.common.exception.UserException;

/**
 * The Class ExceptionControllerAdvice.
 *
 * @author <a href="mailto:Prakash.Bisht@harman.com">Prakash Bisht</a>
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

  /**
   * Exception handler.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> exceptionHandler(final Exception ex) {
    final ErrorResponseDto error = new ErrorResponseDto();
    error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    error.setMessage("Please contact your administrator");
    return new ResponseEntity<ErrorResponseDto>(error, HttpStatus.OK);
  }

  @ExceptionHandler(UserException.class)
  public ResponseEntity<ErrorResponseDto> userExceptionHandler(final UserException ex) {
    final ErrorResponseDto error = new ErrorResponseDto();
    error.setErrorCode(ex.getErroreCode());
    error.setMessage(ex.getMessage());
    return new ResponseEntity<ErrorResponseDto>(error, HttpStatus.OK);
  }
}
