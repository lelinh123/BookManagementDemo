package com.cmc.demo.cucumber.controller.query;


import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.cmc.demo.cucumber.controller.BasicSteps;
import com.cmc.demo.entity.Book;
import com.cmc.demo.exception.BookNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
public class ControllerQuerySteps extends BasicSteps {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private final String BASIC_BOOK_REQUEST_URL = "http://localhost:8080/books/";

  @When("^The client request GET /book api$")
  public void the_client_request_GET_book_api() throws Throwable {

    System.out.println("Request get api: http://localhost:8080/books/");
  }

  @Then("^The response return a lists of book record in database and status code (\\d+);$")
  public void the_response_return_a_lists_of_book_record_in_database_and_status_code(
      int codeStatusExpect) throws Throwable {
    boolean checkException = false;
    try {
      codeStatusExpect = 200;
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> response =
          restTemplate.getForEntity(BASIC_BOOK_REQUEST_URL, String.class);
      assertEquals(codeStatusExpect, response.getStatusCodeValue());
      ObjectMapper objectMapper = new ObjectMapper();
      List<Book> books = objectMapper.readValue(response.getBody(),
          objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));
      assertEquals(6, books.size());
    } catch (BookNotFoundException e) {
      // TODO: handle exception
      checkException = true;
    }
  }

  @When("^The client request GET find by id api$")
  public void the_client_request_GET_find_by_id_api() throws Throwable {
    System.out.println("Request get api: http://localhost:8080/books/id/1");
  }

  @Then("^The response return a  book record by id in database and status code (\\d+);$")
  public void the_response_return_a_book_record_by_id_in_database_and_status_code(
      int codeStatusExpect) throws Throwable {
    codeStatusExpect = 200;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response =
        restTemplate.getForEntity(BASIC_BOOK_REQUEST_URL + "id/1", String.class);
    assertEquals(codeStatusExpect, response.getStatusCodeValue());
    Book book = new ObjectMapper().readValue(response.getBody(), Book.class);
    assertEquals("c007", book.getCode());
    assertEquals("dong ta", book.getName());
  }

  @When("^The client request GET find by code api$")
  public void the_client_request_GET_find_by_code_api() throws Throwable {
    System.out.println("Request get api: http://localhost:8080/books/code/c007");
  }

  @Then("^The response return a  book record by code in database and status code (\\d+);$")
  public void the_response_return_a_book_record_by_code_in_database_and_status_code(
      int codeStatusExpect) throws Throwable {
    codeStatusExpect = 200;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response =
        restTemplate.getForEntity(BASIC_BOOK_REQUEST_URL + "code/c007", String.class);
    assertEquals(codeStatusExpect, response.getStatusCodeValue());
    Book book = new ObjectMapper().readValue(response.getBody(), Book.class);
    assertEquals("c007", book.getCode());
    assertEquals("dong ta", book.getName());
  }

  @When("^The client request GET find by name api$")
  public void the_client_request_GET_find_by_name_api() throws Throwable {
    System.out.println("Request get api: http://localhost:8080/books/name/test");
  }

  @Then("^The response return a list  book record by name in database and status code (\\d+);$")
  public void the_response_return_a_list_book_record_by_name_in_database_and_status_code(
      int codeStatusExpect) throws Throwable {
    codeStatusExpect = 200;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response =
        restTemplate.getForEntity(BASIC_BOOK_REQUEST_URL + "name/test", String.class);
    assertEquals(codeStatusExpect, response.getStatusCodeValue());
    ObjectMapper objectMapper = new ObjectMapper();
    List<Book> books = objectMapper.readValue(response.getBody(),
        objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));
    assertEquals(2, books.size());
  }

  @When("^The client request GET find by category api$")
  public void the_client_request_GET_find_by_category_api() throws Throwable {
    System.out.println("Request get api: http://localhost:8080/books/category/novel");
  }

  @Then("^The response return a list  book record by category in database and status code (\\d+);$")
  public void the_response_return_a_list_book_record_by_category_in_database_and_status_code(
      int codeStatusExpect) throws Throwable {
    codeStatusExpect = 200;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response =
        restTemplate.getForEntity(BASIC_BOOK_REQUEST_URL + "category/novel", String.class);
    assertEquals(codeStatusExpect, response.getStatusCodeValue());
    ObjectMapper objectMapper = new ObjectMapper();
    List<Book> books = objectMapper.readValue(response.getBody(),
        objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));
    assertEquals(2, books.size());
  }

  @When("^The client request GET find by author api$")
  public void the_client_request_GET_find_by_author_api() throws Throwable {
    System.out.println("Request get api: http://localhost:8080/books/author/lhlinh");
  }

  @Then("^The response return a list  book record by author in database and status code (\\d+);$")
  public void the_response_return_a_list_book_record_by_author_in_database_and_status_code(
      int codeStatusExpect) throws Throwable {
    codeStatusExpect = 200;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response =
        restTemplate.getForEntity(BASIC_BOOK_REQUEST_URL + "author/lhlinh", String.class);
    assertEquals(codeStatusExpect, response.getStatusCodeValue());
    ObjectMapper objectMapper = new ObjectMapper();
    List<Book> books = objectMapper.readValue(response.getBody(),
        objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));
    assertEquals(3, books.size());
  }

  @When("^The client request GET find by id less zero$")
  public void the_client_request_GET_find_by_id_less_zero() throws Throwable {
    System.out.println("Request get api: http://localhost:8080/books/id/-1");
  }

  @Then("^The response return HttpStatuscode is (\\d+) and ValidationException;$")
  public void the_response_return_HttpStatuscode_is_and_ValidationException(int expectedStatusCode)
      throws Throwable {
    expectedStatusCode = 500;
    try {
      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<String> response =
          restTemplate.getForEntity(BASIC_BOOK_REQUEST_URL + "id/-1", String.class);
      assertEquals(expectedStatusCode, response.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage("Invalid Book ID: -1");

    }
  }

  @When("^The client request GET find by code is blank$")
  public void the_client_request_GET_find_by_code_is_blank() throws Throwable {
    System.out.println("Request get api: http://localhost:8080/book/code/");
  }

  @Then("^The response return HttpStatuscode is (\\d+) and ValidationException  with messsage Code is blank;$")
  public void the_response_return_HttpStatuscode_is_and_ValidationException_with_messsage_Code_is_blank(
      int expectedStatusCode) throws Throwable {
    expectedStatusCode = 500;
    try {
      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<String> response =
          restTemplate.getForEntity(BASIC_BOOK_REQUEST_URL + "code/\"  \"", String.class);
      assertEquals(expectedStatusCode, response.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage("Code is blank");
    }
  }

  @When("^The client request GET find by name is blank$")
  public void the_client_request_GET_find_by_name_is_blank() throws Throwable {
    System.out.println("Request get api: http://localhost:8080/name");
  }

  @Then("^The response return HttpStatuscode is (\\d+) and ValidationException Name is blank;$")
  public void the_response_return_HttpStatuscode_is_and_ValidationException_Name_is_blank(
      int expectedStatusCode) throws Throwable {
    try {
      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<String> response =
          restTemplate.getForEntity(BASIC_BOOK_REQUEST_URL + "name/%20  ", String.class);
    } catch (HttpServerErrorException e) {
      thrown.expectMessage("Name is blank");
    }
  }

  @When("^The client request GET find by category is blank$")
  public void the_client_request_GET_find_by_category_is_blank() throws Throwable {
    System.out.println("Request get api: http://localhost:8080/books/category");
  }

  @Then("^The response return HttpStatuscode is (\\d+) and ValidationException Category is blank;$")
  public void the_response_return_HttpStatuscode_is_and_ValidationException_Category_is_blank(
      int expectedStatusCode) throws Throwable {
    try {
      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<String> response =
          restTemplate.getForEntity(BASIC_BOOK_REQUEST_URL + "category/%20", String.class);
    } catch (HttpServerErrorException e) {
      thrown.expectMessage("Category is blank");

    } 
  }

  @When("^The client request GET find by author is blank$")
  public void the_client_request_GET_find_by_author_is_blank() throws Throwable {
    System.out.println("Request get api: http://localhost:8080/books/author"); 
  }

  @Then("^The response return HttpStatuscode is (\\d+) and ValidationException Author is blank;$")
  public void the_response_return_HttpStatuscode_is_and_ValidationException_Author_is_blank(
      int expectedStatusCode) throws Throwable {
    try {
      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<String> response =
          restTemplate.getForEntity(BASIC_BOOK_REQUEST_URL + "author/%20", String.class);
    } catch (HttpServerErrorException e) {
      thrown.expectMessage("Author is blank");

    }
  }

  // @Then("^The response return HttpStatuscode is (\\d+) and ValidationException;$")
  // public void the_response_return_HttpStatuscode_is_and_ValidationException(int
  // expectedStatusCode) throws Throwable {
  //
  // expectedStatusCode = 500;
  // try {
  // RestTemplate restTemplate = new RestTemplate();
  //
  // ResponseEntity<String> response = restTemplate.getForEntity(BASIC_USER_REQUEST_URL + "id/-1",
  // String.class);
  // assertEquals(expectedStatusCode, response.getStatusCodeValue());
  // } catch (HttpServerErrorException e) {
  // thrown.expectMessage("Invalid Book ID: -1");
  //
  // }
  //
  // }


  // @Then("the user gets a Null pointer exception$")
  // public void null_exception_thrown() {
  // Assertions.assertThatThrownBy(() -> taskCreater.createTask(null)).
  // isInstanceOf(NullPointerException.class);
  // }


  // boolean result = false;
  // try {
  // Book book = new Book(100, "codexxx", "name01", "description", "category", "author",
  // "publisher",
  // "createUser", "createDate", "updateUser", "updateDate");
  // boolean checkReturn = bookCommandService.createBook(book);
  // assertEquals(true, checkReturn);
  // } catch (Exception e) {
  // result = true;
  // }
  // assertTrue(result);
  // }

}
