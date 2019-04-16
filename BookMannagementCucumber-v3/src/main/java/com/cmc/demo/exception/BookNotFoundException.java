package com.cmc.demo.exception;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends BusinessException {

  /**
   * 
   */
  private static final long serialVersionUID = 4613201497854284734L;

  public BookNotFoundException(String message) {
    super(message, BusinessErrorCodeE.BookNotFound, HttpStatus.NOT_FOUND);
  }

}
