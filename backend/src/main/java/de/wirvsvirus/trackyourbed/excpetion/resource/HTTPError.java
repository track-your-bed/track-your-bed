package de.wirvsvirus.trackyourbed.excpetion.resource;

public class HTTPError {

  private final ErrorCode code;
  private final String errorMessage;

  private HTTPError(ErrorCode code, String errorMessage) {
    this.code = code;
    this.errorMessage = errorMessage;
  }

  public ErrorCode getErrorCode() {
    return code;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  @Override
  public String toString(){
    return code + ": " + errorMessage;
  }
}
