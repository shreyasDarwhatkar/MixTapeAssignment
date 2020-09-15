package com.highspot.mixtape.exception;

public class EntityNotPresentException extends Exception {
  public EntityNotPresentException(String errorMessage) {
    super(errorMessage);
  }
}
