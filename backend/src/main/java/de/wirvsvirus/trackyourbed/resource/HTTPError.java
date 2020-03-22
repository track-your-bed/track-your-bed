package de.wirvsvirus.trackyourbed.resource;

public class HTTPError {

  private final ErrorCode code;
  private final String errorMessage;

  public HTTPError(final ErrorCode code, final String errorMessage) {
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
