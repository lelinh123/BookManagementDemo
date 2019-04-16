package com.cmc.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import com.cmc.demo.entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
  @Async
  Book findById(int id);
  
  @Async
  @Query(value = "select * from Book b where b.code = ?1 ",  nativeQuery = true)
  Book findByCode(String code);
  
  @Async
  @Query(value = "select * from Book b where b.name = ?1 ",  nativeQuery = true)
  List<Book> findByName(String name);
  
  @Async
  @Query(value = "select * from Book b where b.category = ?1 ",  nativeQuery = true)
  List<Book> findByCategory(String category);
  
  @Async
  @Query(value = "select * from Book b where b.author = ?1 ",  nativeQuery = true)
  List<Book> findByAuthor(String author);
}
