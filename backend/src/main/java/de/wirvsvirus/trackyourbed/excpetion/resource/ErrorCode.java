package de.wirvsvirus.trackyourbed.excpetion.resource;

public enum ErrorCode {

  WARD_NOT_FOUND(1, "ward not found"),
  WAARD_TYPE_NOT_FOUND(2, "ward type not found"),
  HOPSITAL_NOT_FOUND(3, "hospital not found"),
  DEPARTMENT_NOT_FOUND(4, "department not found"),
  BED_TYPE_NOT_FOUND(5, "bed type not found"),
  BED_STATE_NOT_FOUND(6, "bed state not found"),
  BED_NOT_FOUND(7, "bed not found");

  int code;
  String description;

  ErrorCode(int code, String description) {
    this.code = code;
    this.description = description;
  }

}
