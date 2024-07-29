package com.project.studentapp.entity;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorCode {
  //정상응답 enum

  OK(2000, "OK", HttpStatus.OK),

  //에러응답 enum
  BAD_REQUEST(5000, "BAD REQUEST", HttpStatus.BAD_REQUEST);

  @Getter
  private final int code;
  @Getter
  private final String message;
  @Getter
  private final HttpStatus httpStatus;


  ErrorCode(int code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }


}
