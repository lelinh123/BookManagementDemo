package com.cmc.demo.controller.query;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cmc.demo.entity.Book;
import com.cmc.demo.service.query.IBookQueryService;


@Controller
public class BookQueryController {
  @Autowired
  IBookQueryService bookService;
  
  @GetMapping(value = "/book/")
  public ResponseEntity<Object> findAll() {
    List<Book> output = bookService.findAll();
    return new ResponseEntity<>(output, HttpStatus.OK);
  }

  @GetMapping(value = "/book/id/{id}")
  public ResponseEntity<Object> findByid(@PathVariable("id") Integer id) {
    Book book = bookService.findById(id);
    return new ResponseEntity<>(book, HttpStatus.OK);
  }
  
  @GetMapping(value = "/book/code/{code}")
  public ResponseEntity<Object> findByCode(@PathVariable("code") String code) {
    Book book = bookService.findByCode(code);
    return new ResponseEntity<>(book, HttpStatus.OK);
  }
  
  @GetMapping(value = "/book/name/{name}")
  public ResponseEntity<Object> findByName(@PathVariable("name") String name) {
    List<Book> output= bookService.findByName(name);
    return new ResponseEntity<>(output, HttpStatus.OK);
  }
  
  @GetMapping(value = "/book/category/{category}")
  public ResponseEntity<Object> findByCategory(@PathVariable("category") String category) {
    List<Book> output= bookService.findByCategory(category);
    return new ResponseEntity<>(output, HttpStatus.OK);
  }
  
  @GetMapping(value = "/book/author/{author}")
  public ResponseEntity<Object> findByAuthor(@PathVariable("author") String author) {
    List<Book> output= bookService.findByAuthor(author);
    return new ResponseEntity<>(output, HttpStatus.OK);
  }
  
}
