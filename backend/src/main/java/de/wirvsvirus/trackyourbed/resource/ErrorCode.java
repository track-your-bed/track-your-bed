package de.wirvsvirus.trackyourbed.resource;

public enum ErrorCode {

  WARD_NOT_FOUND(1, "ward not found"),
  WARD_TYPE_NOT_FOUND(2, "ward type not found"),
  HOSPITAL_NOT_FOUND(3, "hospital not found"),
  DEPARTMENT_NOT_FOUND(4, "department not found"),
  BED_TYPE_NOT_FOUND(5, "bed type not found"),
  BED_STATE_NOT_FOUND(6, "bed state not found"),
  BED_NOT_FOUND(7, "bed not found"),
  DEPARTMENT_TYPE_NOT_FOUND(8, "department type not found");

  private final int code;
  private final String description;

  ErrorCode(final int code, final String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }
}
