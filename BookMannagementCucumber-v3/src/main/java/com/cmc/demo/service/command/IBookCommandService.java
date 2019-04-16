package com.cmc.demo.service.command;

import com.cmc.demo.entity.Book;

public interface IBookCommandService {
  boolean createBook(Book book);
  boolean updateBookById(Book book, Integer id);
  boolean updateBookByCode(Book book, String code);
  boolean deleteBookById(Integer id);
  boolean deleteBookByCode(String code);
}

