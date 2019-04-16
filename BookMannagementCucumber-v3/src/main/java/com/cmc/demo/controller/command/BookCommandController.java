package com.cmc.demo.controller.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cmc.demo.entity.Book;
import com.cmc.demo.service.command.IBookCommandService;


@RestController
public class BookCommandController {
  
  @Autowired
  IBookCommandService iBookCommandService;
  
  @PostMapping("/book/")
  public ResponseEntity<?> createBook(@RequestBody Book book) {
    
      iBookCommandService.createBook(book);
      return new ResponseEntity<>( HttpStatus.CREATED);
  }
  
  @PutMapping("book/update/id/{id}")
  public ResponseEntity<?> updateBookById(@RequestBody Book book, @PathVariable int id) {
    iBookCommandService.updateBookById(book, id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @PutMapping("book/update/code/{code}")
  public ResponseEntity<?> updateBookByCode(@RequestBody Book book, @PathVariable String code) {
    iBookCommandService.updateBookByCode(book, code);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @DeleteMapping("book/delete/id/{id}")
  public ResponseEntity<?> deleteBookById(@PathVariable Integer id){
    iBookCommandService.deleteBookById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @DeleteMapping("book/delete/code/{code}")
  public ResponseEntity<?> deleteBookByCode(@PathVariable String code){
    iBookCommandService.deleteBookByCode(code);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
}
