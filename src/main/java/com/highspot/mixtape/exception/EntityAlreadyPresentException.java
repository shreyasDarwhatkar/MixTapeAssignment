package com.highspot.mixtape.exception;

public class EntityAlreadyPresentException extends Exception {
  public EntityAlreadyPresentException(String errorMessage) {
    super(errorMessage);
  }
}
