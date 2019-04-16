package com.cmc.demo.exception;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BusinessException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -4304172913760181493L;
  protected BusinessErrorCodeE businessErrorCode;
  protected HttpStatus statusCode;
  
  public BusinessException(String message, BusinessErrorCodeE businessErrorCode, HttpStatus statusCode) {
    super(message);
    this.businessErrorCode = businessErrorCode;
    this.statusCode = statusCode;
  }
  
  public BusinessException(String message) {
    super(message);
    this.businessErrorCode = BusinessErrorCodeE.InternalServerError;
    this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
  }
  
  
}
