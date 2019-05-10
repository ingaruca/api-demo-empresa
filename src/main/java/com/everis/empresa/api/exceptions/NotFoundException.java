package com.everis.empresa.api.exceptions;

public class NotFoundException extends GenericExceptionBase {

  public NotFoundException(String code, String message) {
    super(code, message);
  }

}
