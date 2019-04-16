package com.cmc.demo.service.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmc.demo.entity.Book;
import com.cmc.demo.exception.BookNotFoundException;
import com.cmc.demo.exception.BusinessException;
import com.cmc.demo.exception.ValidationException;
import com.cmc.demo.repository.BookRepository;
import com.cmc.demo.service.query.BookQueryServiceImpl;

@Service
public class BookCommandServiceImpl implements IBookCommandService {
  private final static Logger Logger = LoggerFactory.getLogger(BookQueryServiceImpl.class);

  @Autowired
  BookRepository bookRepository;


  @Override
  public boolean createBook(Book book) {

    validateNewBook(book);
    bookRepository.save(book);
    Logger.info("Create book success with uid: " + book.getUid());
    return true;
  }

  @Override
  public boolean updateBookById(Book book, Integer id) {
    validatateUpdateById(book, id);
    bookRepository.save(book);
    return true;
  }

  @Override
  public boolean updateBookByCode(Book book, String code) {
    validatateUpdateByCode(book, code);
    bookRepository.save(book);
    return true;
  }

  @Override
  public boolean deleteBookById(Integer id) {
    validateDeleteById(id);
    bookRepository.deleteById(id);
    return true;
  }

  @Override
  public boolean deleteBookByCode(String code) {
    validateDeleteByCode(code);
    Book book = bookRepository.findByCode(code);
    int id = book.getUid();
    bookRepository.deleteById(id);
    return true;
  }

  public boolean validateDeleteById(Integer id) {
    if (id < 0) {
      throw new ValidationException("Invalid parameter with uid :" + id);
    }
    boolean check = bookRepository.existsById(id);
    if (!check) {
      throw new BusinessException("Book does not exists!");
    }
    return true;
  }


  public boolean validateDeleteByCode(String code) {
    if (code.isBlank()) {
      throw new ValidationException("Invalid parameter, code is blank");
    }
    Book result = bookRepository.findByCode(code);
    if (null == result) {
      throw new BusinessException("Book does not exists!");
    }
    return true;
  }


  public boolean validateNewBook(Book book) {


    // check uid < 0 and exist
    if (book.getUid() < 0) {
      throw new ValidationException("Invalid parameter with uid:" + book.getUid());
    } else {
      boolean check = bookRepository.existsById(book.getUid());
      if (check) {
        throw new ValidationException("Uid is existed");
      }
    }

    // check code blank and exist
    if (book.getCode().isBlank()) {
      throw new ValidationException("Code is blank ");
    } else {
      Book result = bookRepository.findByCode(book.getCode());
      if (null != result) {
        throw new BusinessException("Code is existed!");
      }
    }

    // check null field other

    if (book.getAuthor().isBlank() || book.getCategory().isBlank() || book.getCreateDate().isBlank()
        || book.getCreateUser().isBlank() || book.getDescription().isBlank()
        || book.getName().isBlank() || book.getPublisher().isBlank()
        || book.getUpdateDate().isBlank() || book.getUpdateUser().isBlank()) {
      throw new ValidationException("Invalid parameter. Field can't blank");
    }
    return true;
  }

  public boolean validatateUpdateById(Book book, Integer id) {
    if (id < 0) {
      throw new ValidationException("Invalid parameter with uid < 0");
    }

    // check code blank and exist
    if (book.getCode().isBlank()) {
      throw new ValidationException("Code is blank ");
    }

    // check null field other

    if (book.getAuthor().isBlank() || book.getCategory().isBlank() || book.getCreateDate().isBlank()
        || book.getCreateUser().isBlank() || book.getDescription().isBlank()
        || book.getName().isBlank() || book.getPublisher().isBlank()
        || book.getUpdateDate().isBlank() || book.getUpdateUser().isBlank()) {
      throw new ValidationException("Invalid parameter. Field can't blank");
    }

    if (!bookRepository.existsById(id)) {
      throw new BookNotFoundException("Book not found with uid: " + id);
    }

    return true;
  }

  public boolean validatateUpdateByCode(Book book, String code) {
    if (code.isBlank()) {
      throw new ValidationException("Invalid parameter with code is blank");
    }
    // check uid < 0 and exist
    if (book.getUid() < 0) {
      throw new ValidationException("Invalid parameter with uid :" + book.getUid());
    }

    // check null field other

    if (book.getAuthor().isBlank() || book.getCategory().isBlank() || book.getCreateDate().isBlank()
        || book.getCreateUser().isBlank() || book.getDescription().isBlank()
        || book.getName().isBlank() || book.getPublisher().isBlank()
        || book.getUpdateDate().isBlank() || book.getUpdateUser().isBlank()) {
      throw new ValidationException("Invalid parameter. Field can't blank");
    }

    if (bookRepository.findByCode(code) == null) {
      throw new BookNotFoundException("Book not found with code: " + code);
    }
    return true;
  }

}
