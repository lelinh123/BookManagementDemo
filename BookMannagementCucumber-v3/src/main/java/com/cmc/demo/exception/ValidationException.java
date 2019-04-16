package com.cmc.demo.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends BusinessException {


  /**
   * 
   */
  private static final long serialVersionUID = -1942719857105314519L;

  public ValidationException(String message) {
    super(message, BusinessErrorCodeE.ValidationInvalidParameter, HttpStatus.BAD_REQUEST);
  }
  
}
