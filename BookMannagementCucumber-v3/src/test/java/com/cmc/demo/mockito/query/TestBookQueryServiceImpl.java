package com.cmc.demo.mockito.query;

import static org.junit.Assert.assertEquals;
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
import com.cmc.demo.service.query.BookQueryServiceImpl;
@SpringBootTest
public class TestBookQueryServiceImpl {

  @InjectMocks
  BookQueryServiceImpl bookQueryServiceImpl;
  List<Book> listBooks = new ArrayList<>();
  @Mock
  BookRepository bookRepository;
//  @Before
//  public void init() {
//  
//  Book book01 = new Book(1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
//  Book book02 = new Book(2, "code02", "name", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
//  Book book03 = new Book(3, "code03", "name", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
//  Book book04 = new Book(4, "code04", "name", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
//  Book book05 = new Book(5, "code05", "name", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
//  Book book06 = new Book(6, "code06", "name", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
//  listBooks.add(book01);
//  }
//  @Rule
//  public final ExpectedException exception = ExpectedException.none();
//
//  public class XxxTest {
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @Test
//    public void testFooThrowsIndexOutOfBoundsException() {
//        thrown.expect(IndexOutOfBoundsException.class)
//        //you can test the exception message like
//        thrown.expectMessage("expected messages");
//        foo.doStuff();
//    }
//}
  @Before 
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }


  @Test
  public void testFindById() {
    Book book01 = new Book(1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
   when(bookRepository.findById(1)).thenReturn(book01);
   
   Book output = bookQueryServiceImpl.findById(1);
   assertEquals("name01", output.getName());

  }
  
  @Test(expected = ValidationException.class )
  public void testFindByIdthrowValidationException() {
   bookQueryServiceImpl.findById(-1);

  }
  
  @Test(expected = com.cmc.demo.exception.BookNotFoundException.class )
  public void testFindByIdThrowNotFound() {
   when(bookRepository.findById(2)).thenReturn(null);
   bookQueryServiceImpl.findById(2);

  } 
  
  @Test
  public void testFindAll() {
    List<Book> listBooks = new ArrayList<Book>();
    Book book01 = new Book(1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    Book book02 = new Book(2, "code02", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    listBooks.add(book01);
    listBooks.add(book02);
    when(bookRepository.findAll()).thenReturn(listBooks);
    List<Book> output = bookQueryServiceImpl.findAll();
    assertEquals(2, output.size());
    verify(bookRepository, times(1)).findAll();
  }
  
  @Test(expected = BusinessException.class)
  public void testFindAllThrowException() {
    List<Book> listBooks = null;
    when(bookRepository.findAll()).thenReturn(listBooks);
    List<Book> output = bookQueryServiceImpl.findAll();
    verify(bookRepository, times(0)).findAll();
  }
  
  @Test 
  public void testFindByCode() {
    Book book01 = new Book(1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    when(bookRepository.findByCode("code01")).thenReturn(book01);
    Book result = bookQueryServiceImpl.findByCode("code01");
    assertEquals(1, result.getUid());
    assertEquals("name01", result.getName());
    verify(bookRepository, times(1)).findByCode("code01");
  }
  
  @Test(expected = ValidationException.class)
  public void testFinBycodeThrowValidationException() {
    bookQueryServiceImpl.findByCode("");
  }
  
  @Test(expected = BookNotFoundException.class)
  public void testFinBycodeNotFoundException() {
    when(bookRepository.findByCode("01")).thenReturn(null);
    bookQueryServiceImpl.findByCode("01");
  }
  
  
  @Test
  public void testFindByName() {
    List<Book> listBooks = new ArrayList<Book>();
    Book book01 = new Book(1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    Book book02 = new Book(2, "code02", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    listBooks.add(book01);
    listBooks.add(book02);
    when(bookRepository.findByName("name01")).thenReturn(listBooks);
    List<Book> results = bookQueryServiceImpl.findByName("name01");
    assertEquals(2, results.size());
    assertEquals("name01", results.get(0).getName());
  }
  
  @Test(expected = ValidationException.class)
  public void testFinByNameThrowValidationException() {
    bookQueryServiceImpl.findByName("");
  }
  
  @Test(expected = BookNotFoundException.class)
  public void testFindByNameNotFoundException() {
    when(bookRepository.findByName("name01")).thenReturn(null);
    bookQueryServiceImpl.findByName("name01");
  }
  
  @Test
  public void testFindByCategory() {
    List<Book> listBooks = new ArrayList<Book>();
    Book book01 = new Book(1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    Book book02 = new Book(2, "code02", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    listBooks.add(book01);
    listBooks.add(book02);
    when(bookRepository.findByCategory("category")).thenReturn(listBooks);
    List<Book> results = bookQueryServiceImpl.findByCategory("category");
    assertEquals(2, results.size());
    assertEquals("category", results.get(0).getCategory());
  }
  
  @Test(expected = ValidationException.class)
  public void testFindByCategoryThrowValidationException() {
    bookQueryServiceImpl.findByCategory("  ");
  }
  
  @Test(expected = BookNotFoundException.class)
  public void testFindByCategoryNotFoundException() {
    when(bookRepository.findByCategory("category")).thenReturn(null);
    bookQueryServiceImpl.findByCategory("category");
  }
  
  @Test
  public void testFindByAuthor() {
    List<Book> listBooks = new ArrayList<Book>();
    Book book01 = new Book(1, "code01", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    Book book02 = new Book(2, "code02", "name01", "description", "category", "author", "publisher", "createUser", "createDate", "updateUser", "updateDate");
    listBooks.add(book01);
    listBooks.add(book02);
    when(bookRepository.findByAuthor("author")).thenReturn(listBooks);
    List<Book> results = bookQueryServiceImpl.findByAuthor("author");
    assertEquals(2, results.size());
    assertEquals("author", results.get(0).getAuthor());
  }
  
  @Test(expected = ValidationException.class)
  public void testFindByAuthorThrowValidationException() {
    bookQueryServiceImpl.findByAuthor("  ");
  }
  
  @Test(expected = BookNotFoundException.class)
  public void testFindByAuthorNotFoundException() {
    when(bookRepository.findByAuthor("author")).thenReturn(null);
    bookQueryServiceImpl.findByAuthor("author");
  }
}
