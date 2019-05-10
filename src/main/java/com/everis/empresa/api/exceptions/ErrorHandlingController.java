package com.everis.empresa.api.exceptions;

import com.everis.empresa.api.exceptions.bean.CustomExceptionBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

  @ExceptionHandler({GenericExceptionBase.class})
  public ResponseEntity<Object> handleNotFound(Exception ex, HttpServletRequest request) {
    CustomExceptionBean customExceptionBean = new CustomExceptionBean();
    customExceptionBean.setTimestamp(LocalDateTime.now());
    customExceptionBean.setCode(HttpStatus.NOT_FOUND.value());
    customExceptionBean.setMessage(ex.getMessage());
    customExceptionBean.setError(ex.getClass().getCanonicalName());
    customExceptionBean.setPath(request.getServletPath());
    return new ResponseEntity<>(customExceptionBean, HttpStatus.NOT_FOUND);
  }

}
