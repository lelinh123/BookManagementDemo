package com.cmc.demo.mockito.command;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.cmc.demo.entity.Book;
import com.cmc.demo.exception.BookNotFoundException;
import com.cmc.demo.exception.BusinessException;
import com.cmc.demo.exception.ValidationException;
import com.cmc.demo.repository.BookRepository;
import com.cmc.demo.service.command.BookCommandServiceImpl;

@SpringBootTest
public class TestBookCommandServiceImpl {

  @InjectMocks
  BookCommandServiceImpl bookCommandServiceImpl;
  
  @Mock
  BookRepository bookRepository;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }
    
  @Test
  public void testCreateBook() {
    Book book01 = new Book(1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    bookCommandServiceImpl.createBook(book01);
    verify(bookRepository, times(1)).save(book01);
  }
  
  
  @Test(expected = ValidationException.class )
  public void testCreateBookThrow02() {
    Book book01 = new Book(-1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    bookCommandServiceImpl.createBook(book01);

  }
  
  @Test(expected = ValidationException.class )
  public void testCreateBookThrow03() {
    Book book01 = new Book(1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    when(bookRepository.existsById(1)).thenReturn(true);
    bookCommandServiceImpl.createBook(book01);
  }
  
  @Test(expected = ValidationException.class )
  public void testCreateBookThrow04() {
    Book book01 = new Book(1, "", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    bookCommandServiceImpl.createBook(book01);
  }
  
  @Test(expected = BusinessException.class )
  public void testCreateBookThrow05() {
    List<Book> listBooks = new ArrayList<Book>();
    Book book01 = new Book(1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    Book book02 = new Book(2, "code02", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    listBooks.add(book01);
    listBooks.add(book02);
    when(bookRepository.findByCode("code01")).thenReturn(book01);
    bookCommandServiceImpl.createBook(book01);

  }
  
  @Test(expected = ValidationException.class )
  public void testCreateBookThrow06() {
    Book book01 = new Book(1, "code01", "  ", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    bookCommandServiceImpl.createBook(book01);

  }

  @Test
  public void testUpdateBookById() {
    Book book01 = new Book(1, "code01", "test update", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    when(bookRepository.existsById(1)).thenReturn(true);
    when(bookRepository.save(book01)).thenReturn(book01);
    boolean check = bookCommandServiceImpl.updateBookById(book01, 1);
    verify(bookRepository, times(1)).save(book01);
    assertTrue(check);
  }

  @Test(expected = ValidationException.class )
  public void testUpdateBookByIdThrow01() {
    Book book01 = new Book(-1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    bookCommandServiceImpl.updateBookById(book01, -1);

  }
  
  @Test(expected = ValidationException.class )
  public void testUpdateBookByIdThrow02() {
    Book book01 = new Book(1, "", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    bookCommandServiceImpl.updateBookById(book01, 1);
  }
  
  @Test(expected = ValidationException.class )
  public void testUpdateBookByIdThrow03() {
    Book book01 = new Book(1, "code01", "", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    bookCommandServiceImpl.updateBookById(book01, 1);

  }
  
  @Test(expected = BookNotFoundException.class )
  public void testUpdateBookByIdThrow04() {
    Book book01 = new Book(656, "code01", "dadasdsa", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    when(bookRepository.existsById(656)).thenReturn(false);
    bookCommandServiceImpl.updateBookById(book01, 656);

  }
  
  @Test
  public void testUpdateBookByCode() {
    Book book01 = new Book(1, "code01", "test update", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    when(bookRepository.findByCode("code01")).thenReturn(book01);
    boolean check = bookCommandServiceImpl.updateBookByCode(book01, "code01");
    verify(bookRepository, times(1)).save(book01);
    assertTrue(check);
  } 

  @Test(expected = ValidationException.class )
  public void testUpdateBookByCodeThrow01() {
    Book book01 = new Book(-1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    bookCommandServiceImpl.updateBookByCode(book01, "code01");

  } 
  
  @Test(expected = ValidationException.class )
  public void testUpdateBookByCodeThrow02() {
    Book book01 = new Book(1, "", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    bookCommandServiceImpl.updateBookByCode(book01, "");

  }
  
  @Test(expected = ValidationException.class )
  public void testUpdateBookByCodeThrow03() {
    Book book01 = new Book(1, "code01", "", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    bookCommandServiceImpl.updateBookByCode(book01, "code01");
  }
  
  @Test(expected = BookNotFoundException.class )
  public void testUpdateBookByCodeThrow04() {
    Book book01 = new Book(1, "asdasd", "dasaas", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    when(bookRepository.findByCode("code01")).thenReturn(null);
    bookCommandServiceImpl.updateBookByCode(book01, "code01");
  }
  
  @Test
  public void testDeleteById() {

    when(bookRepository.existsById(1)).thenReturn(true);
    boolean check = bookCommandServiceImpl.deleteBookById(1);
    verify(bookRepository, times(1)).deleteById(1);
    assertTrue(check);
  }
  
  @Test(expected = ValidationException.class )
  public void testDeleteByIdThrow01() {
    bookCommandServiceImpl.deleteBookById(-1);
  }
  
  @Test(expected = BusinessException.class )
  public void testDeleteByIdThrow02() {
   
    when(bookRepository.existsById(1)).thenReturn(false);
    bookCommandServiceImpl.deleteBookById(1);
  }
  
  @Test
  public void testDeleteByCode() {
    Book book01 = new Book(1, "code01", "test update", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    when(bookRepository.findByCode("code01")).thenReturn(book01);
    boolean check = bookCommandServiceImpl.deleteBookByCode("code01");
    verify(bookRepository, times(1)).deleteById(1);
    assertTrue(check);
  }
  
  @Test(expected = ValidationException.class )
  public void testDeleteByCodeThrow01() {
    bookCommandServiceImpl.deleteBookByCode("");
  }
  
  @Test(expected = BusinessException.class )
  public void testDeleteByCodeThrow02() {
    when(bookRepository.findByCode("code01")).thenReturn(null);
    bookCommandServiceImpl.deleteBookByCode("code01");
  }
  
}
