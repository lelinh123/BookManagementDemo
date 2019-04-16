package com.cmc.demo.service.query;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmc.demo.entity.Book;
import com.cmc.demo.exception.BookNotFoundException;
import com.cmc.demo.exception.ValidationException;
import com.cmc.demo.repository.BookRepository;
@Service
public class BookQueryServiceImpl implements IBookQueryService {

  @Autowired
  BookRepository bookRepository;
  @Override
  public Book findById(int id) {
    Book output = null;
    validationFindById(id);
    output = bookRepository.findById(id); 
    
    if (null == output) {
      //throw new BookException.BookNotFoundException();
      throw new BookNotFoundException("Cannot find book with ID: " + id);
    }
    return output;
  }
  
  @Override
  public List<Book> findAll() {
    List<Book> output = bookRepository.findAll();
    if (null == output) {
      throw new BookNotFoundException("Cannot find book");
    }
    return output;
  }
  
  private boolean validationFindById(int bookId) {
    if(bookId < 1)
      throw new ValidationException("Invalid Book ID: " + bookId);
    return true;
  }
 
  @Override
  public Book findByCode(String code) {
    if (code.isBlank()) {
      throw new ValidationException("Code is blank");
    }
    Book output = bookRepository.findByCode(code);
    if (null == output) {
      throw new BookNotFoundException("Cannot find book with Code: " + code);
    }
    return output; 
  }
 
  @Override
  public List<Book> findByName(String name) {
    if (name.isBlank()) {
      throw new ValidationException("Name is blank"); 
    }
    List<Book> output = bookRepository.findByName(name);
    if (null == output) {
      throw new BookNotFoundException("Cannot find book with name: " + name);
    }
    return output;
  }
 
  @Override
  public List<Book> findByCategory(String category) {
    if (category.isBlank()) {
      throw new ValidationException("Name is blank");
    }
    List<Book> output = bookRepository.findByCategory(category);
    if (null == output) {
      throw new BookNotFoundException("Cannot find book with category: " + category);
    }
    return output;
  }
 
  @Override
  public List<Book> findByAuthor(String author) {
    if (author.isBlank()) {
      throw new ValidationException("Author is blank");
    }
    List<Book> output = bookRepository.findByAuthor(author);
    if (null == output) {
      throw new BookNotFoundException("Cannot find book with author: " + author);
    }
    return output;
  }
  
  
}
