package com.enums;

public enum ErrorCode {
  USER_NOTFOUND("User does not exit", 500), ACCESS_DENIED("not allowed",501);

  private final int errorCode;
  private final String errorMessage;

  ErrorCode(final String errorMessage, final int errorCode) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }



}
