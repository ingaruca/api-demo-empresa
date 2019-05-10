package com.everis.empresa.api.exceptions;

public class GenericExceptionBase extends RuntimeException {

  private String code;

  public GenericExceptionBase(String code, String message) {
    super(message);
    this.code = code;
  }

}
