package com.highspot.mixtape.Exception;

public class EntityAlreadyPresentException extends Exception {
  public EntityAlreadyPresentException(String errorMessage) {
    super(errorMessage);
  }
}
