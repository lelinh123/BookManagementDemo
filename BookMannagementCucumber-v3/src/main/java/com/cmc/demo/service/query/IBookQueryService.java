package com.cmc.demo.service.query;

import java.util.List;
import com.cmc.demo.entity.Book;

public interface IBookQueryService {
  Book findById(int id);
  Book findByCode(String code);
  List<Book> findAll(); 
  List<Book> findByName(String name);
  List<Book> findByCategory(String category);
  List<Book> findByAuthor(String author);
}
