package com.everis.empresa.api.exceptions.bean;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomExceptionBean {

  private int code;
  private String message;
  private LocalDateTime timestamp;
  private String path;
  private String error;

  public CustomExceptionBean() {
  }

  public CustomExceptionBean(int code, String message, LocalDateTime timestamp, String path, String error) {
    this.code = code;
    this.message = message;
    this.timestamp = timestamp;
    this.path = path;
    this.error = error;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

}
