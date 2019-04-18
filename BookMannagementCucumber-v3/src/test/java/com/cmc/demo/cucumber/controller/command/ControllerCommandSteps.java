package com.cmc.demo.cucumber.controller.command;

import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.cmc.demo.cucumber.controller.BasicSteps;
import com.cmc.demo.entity.Book;
import com.cmc.demo.exception.BookNotFoundException;
import com.cmc.demo.service.command.IBookCommandService;
import com.cmc.demo.service.query.IBookQueryService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ControllerCommandSteps extends BasicSteps {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Autowired
  IBookCommandService iBookCommandService;

  @Autowired
  IBookQueryService iBookQueryService;



  private final String BASIC_BOOK_REQUEST_URL = "http://localhost:8080/books/";
  private static Book sampleBook = new Book();

  @Given("^The client request POST book/create$")
  public void the_client_request_POST_book_create() throws Throwable {
    System.out.println("Request post api: http://localhost:8080/books/ ");
  }

  @When("^book detail: uid: (\\d+), code: \"([^\"]*)\",name: \"([^\"]*)\", description: \"([^\"]*)\", category: \"([^\"]*)\",  author: \"([^\"]*)\", publisher: \"([^\"]*)\",  createUser: \"([^\"]*)\", createDate: \"([^\"]*)\", updateUser: \"([^\"]*)\", updateDate: \"([^\"]*)\"$")
  public void book_detail_uid_code_name_description_category_author_publisher_createUser_createDate_updateUser_updateDate(
      int uid, String code, String name, String description, String category, String author,
      String publisher, String createUser, String createDate, String updateUser, String updateDate)
      throws Throwable {
    uid = 999;
    code = "b99999";
    name = "thuy hu";
    description = "tong giang truyen";
    category = "tieu thuyet tien hiep";
    author = "ngoa long";
    publisher = "nxb lao dong";
    createUser = "lhlinh";
    createDate = "25/07/1991";
    updateUser = "lhlinh";
    updateDate = "25/07/1991";

    sampleBook.setUid(uid);
    sampleBook.setCode(code);
    sampleBook.setName(name);
    sampleBook.setCategory(author);
    sampleBook.setDescription(category);
    sampleBook.setAuthor(author);
    sampleBook.setPublisher(publisher);
    sampleBook.setCreateUser(createUser);
    sampleBook.setCreateDate(createDate);
    sampleBook.setUpdateUser(updateUser);
    sampleBook.setUpdateDate(updateDate);
  }

  @Then("^The response return code (\\d+) and the new book exist in database$")
  public void the_response_return_code_and_the_new_book_exist_in_database(int codeStatusExpect)
      throws Throwable {
    codeStatusExpect = 201;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response =
        restTemplate.postForEntity(BASIC_BOOK_REQUEST_URL, sampleBook, String.class);
    assertEquals(codeStatusExpect, response.getStatusCodeValue());
    iBookCommandService.deleteBookByCode("b99999");

  }

  @Given("^The client request POST book/create with id invalid$")
  public void the_client_request_POST_book_create_with_id_invalid() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/books/ ");
  }

  @When("^create book with id invalid: uid: -(\\d+), code: \"([^\"]*)\",name: \"([^\"]*)\", description: \"([^\"]*)\", category: \"([^\"]*)\",  author: \"([^\"]*)\", publisher: \"([^\"]*)\",  createUser: \"([^\"]*)\", createDate: \"([^\"]*)\", updateUser: \"([^\"]*)\", updateDate: \"([^\"]*)\"$")
  public void create_book_with_id_invalid_uid_code_name_description_category_author_publisher_createUser_createDate_updateUser_updateDate(
      int uid, String code, String name, String description, String category, String author,
      String publisher, String createUser, String createDate, String updateUser, String updateDate)
      throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    uid = -1;
    code = "b0410";
    name = "thuy hu";
    description = "tong giang truyen";
    category = "tieu thuyet tien hiep";
    author = "ngoa long";
    publisher = "nxb lao dong";
    createUser = "lhlinh";
    createDate = "25/07/1991";
    updateUser = "lhlinh";
    updateDate = "25/07/1991";

    sampleBook.setUid(uid);
    sampleBook.setCode(code);
    sampleBook.setName(name);
    sampleBook.setCategory(author);
    sampleBook.setDescription(category);
    sampleBook.setAuthor(author);
    sampleBook.setPublisher(publisher);
    sampleBook.setCreateUser(createUser);
    sampleBook.setCreateDate(createDate);
    sampleBook.setUpdateUser(updateUser);
    sampleBook.setUpdateDate(updateDate);
  }

  @Then("^The response return code (\\d+) and Validation Exception with message for uid invalid: \"([^\"]*)\"$")
  public void the_response_return_code_and_Validation_Exception_with_message_for_uid_invalid(
      int codeStatusExpect, String expectedMessage) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeStatusExpect = 500;
    expectedMessage = "Invalid parameter with uid: -1";
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> response =
          restTemplate.postForEntity(BASIC_BOOK_REQUEST_URL, sampleBook, String.class);
      assertEquals(codeStatusExpect, response.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(expectedMessage);

    }
  }

  @Given("^The client request POST book/create with id existed$")
  public void the_client_request_POST_book_create_with_id_existed() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/books/ ");
  }

  @When("^create book with id existed: uid: (\\d+), code: \"([^\"]*)\",name: \"([^\"]*)\", description: \"([^\"]*)\", category: \"([^\"]*)\",  author: \"([^\"]*)\", publisher: \"([^\"]*)\",  createUser: \"([^\"]*)\", createDate: \"([^\"]*)\", updateUser: \"([^\"]*)\", updateDate: \"([^\"]*)\"$")
  public void create_book_with_id_existed_uid_code_name_description_category_author_publisher_createUser_createDate_updateUser_updateDate(
      int uid, String code, String name, String description, String category, String author,
      String publisher, String createUser, String createDate, String updateUser, String updateDate)
      throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    uid = 1;
    code = "b9999";
    name = "thuy hu";
    description = "tong giang truyen";
    category = "tieu thuyet tien hiep";
    author = "ngoa long";
    publisher = "nxb lao dong";
    createUser = "lhlinh";
    createDate = "25/07/1991";
    updateUser = "lhlinh";
    updateDate = "25/07/1991";

    sampleBook.setUid(uid);
    sampleBook.setCode(code);
    sampleBook.setName(name);
    sampleBook.setCategory(author);
    sampleBook.setDescription(category);
    sampleBook.setAuthor(author);
    sampleBook.setPublisher(publisher);
    sampleBook.setCreateUser(createUser);
    sampleBook.setCreateDate(createDate);
    sampleBook.setUpdateUser(updateUser);
    sampleBook.setUpdateDate(updateDate);

  }

  @Then("^The response return code (\\d+) and Validation Exception with message for uid existed: \"([^\"]*)\"$")
  public void the_response_return_code_and_Validation_Exception_with_message_for_uid_existed(
      int codeStatusExpect, String expectedMessage) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeStatusExpect = 500;
    expectedMessage = "Uid is existed";
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> response =
          restTemplate.postForEntity(BASIC_BOOK_REQUEST_URL, sampleBook, String.class);
      assertEquals(codeStatusExpect, response.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(expectedMessage);
    }
  }

  @Given("^The client request POST book/create with code was exist$")
  public void the_client_request_POST_book_create_with_code_was_exist() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/books/ ");
  }

  @When("^create book with code was exist : uid: (\\d+), code: \"([^\"]*)\",name: \"([^\"]*)\", description: \"([^\"]*)\", category: \"([^\"]*)\",  author: \"([^\"]*)\", publisher: \"([^\"]*)\",  createUser: \"([^\"]*)\", createDate: \"([^\"]*)\", updateUser: \"([^\"]*)\", updateDate: \"([^\"]*)\"$")
  public void create_book_with_code_was_exist_uid_code_name_description_category_author_publisher_createUser_createDate_updateUser_updateDate(
      int uid, String code, String name, String description, String category, String author,
      String publisher, String createUser, String createDate, String updateUser, String updateDate)
      throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    uid = 999;
    code = "c007";
    name = "thuy hu";
    description = "tong giang truyen";
    category = "tieu thuyet tien hiep";
    author = "ngoa long";
    publisher = "nxb lao dong";
    createUser = "lhlinh";
    createDate = "25/07/1991";
    updateUser = "lhlinh";
    updateDate = "25/07/1991";

    sampleBook.setUid(uid);
    sampleBook.setCode(code);
    sampleBook.setName(name);
    sampleBook.setCategory(author);
    sampleBook.setDescription(category);
    sampleBook.setAuthor(author);
    sampleBook.setPublisher(publisher);
    sampleBook.setCreateUser(createUser);
    sampleBook.setCreateDate(createDate);
    sampleBook.setUpdateUser(updateUser);
    sampleBook.setUpdateDate(updateDate);
  }

  @Then("^The response return code (\\d+) and Validation Exception with message for code exited: \"([^\"]*)\"$")
  public void the_response_return_code_and_Validation_Exception_with_message_for_code_exited(
      int codeStatusExpect, String expectedMessage) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeStatusExpect = 500;
    expectedMessage = "Code is existed";
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> response =
          restTemplate.postForEntity(BASIC_BOOK_REQUEST_URL, sampleBook, String.class);
      assertEquals(codeStatusExpect, response.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(expectedMessage);
    }
  }

  @Given("^The client request POST book/create with code is blank$")
  public void the_client_request_POST_book_create_with_code_is_blank() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/book/ ");
  }

  @When("^create book code is blank: uid: (\\d+), code: \"([^\"]*)\",name: \"([^\"]*)\", description: \"([^\"]*)\", category: \"([^\"]*)\",  author: \"([^\"]*)\", publisher: \"([^\"]*)\",  createUser: \"([^\"]*)\", createDate: \"([^\"]*)\", updateUser: \"([^\"]*)\", updateDate: \"([^\"]*)\"$")
  public void create_book_code_is_blank_uid_code_name_description_category_author_publisher_createUser_createDate_updateUser_updateDate(
      int uid, String code, String name, String description, String category, String author,
      String publisher, String createUser, String createDate, String updateUser, String updateDate)
      throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    uid = 999;
    code = " ";
    name = "thuy hu";
    description = "tong giang truyen";
    category = "tieu thuyet tien hiep";
    author = "ngoa long";
    publisher = "nxb lao dong";
    createUser = "lhlinh";
    createDate = "25/07/1991";
    updateUser = "lhlinh";
    updateDate = "25/07/1991";

    sampleBook.setUid(uid);
    sampleBook.setCode(code);
    sampleBook.setName(name);
    sampleBook.setCategory(author);
    sampleBook.setDescription(category);
    sampleBook.setAuthor(author);
    sampleBook.setPublisher(publisher);
    sampleBook.setCreateUser(createUser);
    sampleBook.setCreateDate(createDate);
    sampleBook.setUpdateUser(updateUser);
    sampleBook.setUpdateDate(updateDate);
  }

  @Then("^The response return code (\\d+) and Validation Exception with message for code is blank : \"([^\"]*)\"$")
  public void the_response_return_code_and_Validation_Exception_with_message_for_code_is_blank(
      int codeStatusExpect, String expectedMessage) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeStatusExpect = 500;
    expectedMessage = "Code is blank";
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> response =
          restTemplate.postForEntity(BASIC_BOOK_REQUEST_URL, sampleBook, String.class);
      assertEquals(codeStatusExpect, response.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(expectedMessage);
    }
  }

  @Given("^The client request POST book/create with field other is blank$")
  public void the_client_request_POST_book_create_with_field_other_is_blank() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/book/ ");
  }

  @When("^create book with field other is blank: uid: (\\d+), code: \"([^\"]*)\",name: \"([^\"]*)\", description: \"([^\"]*)\", category: \"([^\"]*)\",  author: \"([^\"]*)\", publisher: \"([^\"]*)\",  createUser: \"([^\"]*)\", createDate: \"([^\"]*)\", updateUser: \"([^\"]*)\", updateDate: \"([^\"]*)\"$")
  public void create_book_with_field_other_is_blank_uid_code_name_description_category_author_publisher_createUser_createDate_updateUser_updateDate(
      int uid, String code, String name, String description, String category, String author,
      String publisher, String createUser, String createDate, String updateUser, String updateDate)
      throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    uid = 999;
    code = "b9999";
    name = "thuy hu";
    description = "   ";
    category = "tieu thuyet tien hiep";
    author = "ngoa long";
    publisher = "nxb lao dong";
    createUser = "lhlinh";
    createDate = "25/07/1991";
    updateUser = "lhlinh";
    updateDate = "25/07/1991";

    sampleBook.setUid(uid);
    sampleBook.setCode(code);
    sampleBook.setName(name);
    sampleBook.setCategory(category);
    sampleBook.setDescription(description);
    sampleBook.setAuthor(author);
    sampleBook.setPublisher(publisher);
    sampleBook.setCreateUser(createUser);
    sampleBook.setCreateDate(createDate);
    sampleBook.setUpdateUser(updateUser);
    sampleBook.setUpdateDate(updateDate);
  }

  @Then("^The response return code (\\d+) and Validation Exception with message for other blank: \"([^\"]*)\"$")
  public void the_response_return_code_and_Validation_Exception_with_message_for_other_blank(
      int codeStatusExpect, String expectedMessage) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeStatusExpect = 500;
    expectedMessage = "Invalid parameter. Field can't blank";
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> response =
          restTemplate.postForEntity(BASIC_BOOK_REQUEST_URL, sampleBook, String.class);
      assertEquals(codeStatusExpect, response.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(expectedMessage);
    }
  }

  @Given("^The client request POST book/update valid$")
  public void the_client_request_POST_book_update_valid() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/books/update/id/1");
  }

  @When("^update book with uid: (\\d+), modify name: \"([^\"]*)\"$")
  public void update_book_with_uid_modify_name(int uid, String arg2) throws Throwable {

    sampleBook.setUid(1);
    sampleBook.setCode("b123");
    sampleBook.setName("Hong Lau Mong");
    sampleBook.setCategory("tieu thuyet tien hiep");
    sampleBook.setDescription("update");
    sampleBook.setAuthor("ngoa long");
    sampleBook.setPublisher("nxb lao dong");
    sampleBook.setCreateUser("lhlinh");
    sampleBook.setCreateDate("25/07/1991");
    sampleBook.setUpdateUser("lhlinh");
    sampleBook.setUpdateDate("25/07/1991");

  }

  @Then("^The response return code (\\d+) and name of book with id (\\d+) id modified to \"([^\"]*)\"  in database$")
  public void the_response_return_code_and_name_of_book_with_id_id_modified_to_in_database(
      int codeStatusExpect, int uid, String nameExpect) throws Throwable {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity =
        restTemplate.exchange("http://localhost:8080/books/update/id/1", HttpMethod.PUT,
            new HttpEntity<>(sampleBook), String.class, uid);
    assertEquals(codeStatusExpect, responseEntity.getStatusCodeValue());
    Book book = iBookQueryService.findById(1);
    assertEquals(nameExpect, book.getName());
  }

  @Given("^The client request POST book/update/id with id invalid$")
  public void the_client_request_POST_book_update_id_with_id_invalid() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/books/update/id/-1 ");
  }

  @When("^update book with uid invalid: uid = -(\\d+)$")
  public void update_book_with_uid_invalid_uid(int uid) throws Throwable {
    uid = -1;
    sampleBook.setUid(uid);
    // Write code here that turns the phrase above into concrete actions

  }

  @Then("^The response return code (\\d+) and Validation Exception with message for uid invalid less than zero : \"([^\"]*)\"$")
  public void the_response_return_code_and_Validation_Exception_with_message_for_uid_invalid_less_than_zero(
      int uid, String messageException) throws Throwable {
    uid = -1;
    messageException = "Invalid parameter with uid < 0";
    // Write code here that turns the phrase above into concrete actions
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
          restTemplate.exchange("http://localhost:8080/books/update/id/-1", HttpMethod.PUT,
              new HttpEntity<>(sampleBook), String.class, uid);
      assertEquals(500, responseEntity.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(messageException);
    }

  }

  @Given("^The client request POST book/update/id with code invalid$")
  public void the_client_request_POST_book_update_id_with_code_invalid() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/books/update/id/1 ");
  }

  @When("^update book by id have code invalid: id = (\\d+), code = \"([^\"]*)\"$")
  public void update_book_by_id_have_code_invalid_id_code(int uid, String code) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    uid = 1;
    code = "";
    sampleBook.setUid(1);
    sampleBook.setCode(code);
  }

  @Then("^The response return code (\\d+) and Validation Exception with message for code invalid : \"([^\"]*)\"$")
  public void the_response_return_code_and_Validation_Exception_with_message_for_code_invalid(
      int codeExpectStatus, String messageException) throws Throwable {
    codeExpectStatus = 500;
    messageException = "Code is blank ";
    // Write code here that turns the phrase above into concrete actions
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
          restTemplate.exchange("http://localhost:8080/books/update/id/1", HttpMethod.PUT,
              new HttpEntity<>(sampleBook), String.class, 1);
      assertEquals(500, responseEntity.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(messageException);
    }
  }

  @Given("^The client request POST book/update/id with field name invalid$")
  public void the_client_request_POST_book_update_id_with_field_name_invalid() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/book/update/id/1 ");
  }

  @When("^update book by id have name blank: id = (\\d+), name = \"([^\"]*)\"$")
  public void update_book_by_id_have_name_blank_id_name(int uid, String name) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    uid = 1;
    name = "";
    sampleBook.setUid(1);
    sampleBook.setName(name);
  }

  @Then("^The response return code (\\d+) and Validation Exception with message for filed name blank : \"([^\"]*)\"$")
  public void the_response_return_code_and_Validation_Exception_with_message_for_filed_name_blank(
      int codeExpectStatus, String messageException) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeExpectStatus = 500;
    messageException = "Invalid parameter. Field can't blank ";
    // Write code here that turns the phrase above into concrete actions
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
          restTemplate.exchange("http://localhost:8080/books/update/id/1", HttpMethod.PUT,
              new HttpEntity<>(sampleBook), String.class, 1);
      assertEquals(500, responseEntity.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(messageException);
    }
  }

  @Given("^The client request POST book/update/id with id not exists$")
  public void the_client_request_POST_book_update_id_with_id_not_exists() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/book/update/id/656 ");
  }

  @When("^update book id not exist: id = (\\d+)$")
  public void update_book_id_not_exist_id(int uid) throws Throwable {
    uid = 656;
    sampleBook.setUid(uid);

  }

  @Then("^The response return code (\\d+) and Validation Exception with message for id not exists : \"([^\"]*)\"$")
  public void the_response_return_code_and_Validation_Exception_with_message_for_id_not_exists(
      int codeExpectStatus, String messageException) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeExpectStatus = 500;
    messageException = "Book not found with uid: 656 ";
    // Write code here that turns the phrase above into concrete actions
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
          restTemplate.exchange("http://localhost:8080/books/update/id/656", HttpMethod.PUT,
              new HttpEntity<>(sampleBook), String.class, 656);
      assertEquals(500, responseEntity.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(messageException);
    }
  }

  @Given("^The client request POST book/update/code code valid$")
  public void the_client_request_POST_book_update_code_code_valid() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/books/update/code/b001");
  }

  @When("^update book with code: \"([^\"]*)\", modify name: \"([^\"]*)\"$")
  public void update_book_with_code_modify_name(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions

    sampleBook.setUid(2);
    sampleBook.setCode("b001");
    sampleBook.setName("Tam Quoc");
    sampleBook.setCategory("tieu thuyet tien hiep");
    sampleBook.setDescription("update 2");
    sampleBook.setAuthor("ngoa long");
    sampleBook.setPublisher("nxb lao dong");
    sampleBook.setCreateUser("lhlinh");
    sampleBook.setCreateDate("25/07/1991");
    sampleBook.setUpdateUser("lhlinh");
    sampleBook.setUpdateDate("25/07/1991");
  }

  @Then("^The response return code (\\d+) and name of book had modified to in database$")
  public void the_response_return_code_and_name_of_book_had_modified_to_in_database(
      int codeStatusExpect) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeStatusExpect = 200;
    String code = "b001";
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity =
        restTemplate.exchange("http://localhost:8080/books/update/code/b001", HttpMethod.PUT,
            new HttpEntity<>(sampleBook), String.class, code);
    assertEquals(codeStatusExpect, responseEntity.getStatusCodeValue());
    Book book = iBookQueryService.findByCode(code);
    assertEquals("Tam Quoc", book.getName());
  }

  @Given("^The client request POST book/update/code for Update book by code invalid$")
  public void the_client_request_POST_book_update_code_for_Update_book_by_code_invalid()
      throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/books/update/code/ ");
  }

  @When("^update book Update book by code is blank: code is blank$")
  public void update_book_Update_book_by_code_is_blank_code_is_blank() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    String code = "";
    sampleBook.setCode(code);
  }

  @Then("^The response return code (\\d+) and Validation Exception with message for update book by code invalid  : \"([^\"]*)\"$")
  public void the_response_return_code_and_Validation_Exception_with_message_for_update_book_by_code_invalid(
      int codeExpectStatus, String messageException) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeExpectStatus = 500;
    messageException = "Invalid parameter with code is blank";
    // Write code here that turns the phrase above into concrete actions
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
          restTemplate.exchange("http://localhost:8080/books/update/code/%20%20", HttpMethod.PUT,
              new HttpEntity<>(sampleBook), String.class, " ");
      assertEquals(500, responseEntity.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(messageException);
    }
  }

  @Given("^The client request POST book/update/code for update by code have name blank$")
  public void the_client_request_POST_book_update_code_for_update_by_code_have_name_blank()
      throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/book/update/code/b001");
  }

  @When("^update book by code have name blank: code = \"([^\"]*)\", name = \"([^\"]*)\"$")
  public void update_book_by_code_have_name_blank_code_name(String code, String name)
      throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    code = "b001";
    name = " ";
    sampleBook.setCode(code);
    sampleBook.setName(name);
  }

  @Then("^The response return code (\\d+) and Validation Exception with message for update by code have name blank : \"([^\"]*)\"$")
  public void the_response_return_code_and_Validation_Exception_with_message_for_update_by_code_have_name_blank(
      int codeExpectStatus, String messageException) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeExpectStatus = 500;
    messageException = "Invalid parameter. Field can't blank";
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
          restTemplate.exchange("http://localhost:8080/books/update/code/b001", HttpMethod.PUT,
              new HttpEntity<>(sampleBook), String.class, "b001");
      assertEquals(500, responseEntity.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(messageException);
    }
  }

  @Given("^The client request POST book/update/code for update code not exists$")
  public void the_client_request_POST_book_update_code_for_update_code_not_exists()
      throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request post api: http://localhost:8080/books/update/code/656");
  }

  @When("^update book by code not exists: code = \"([^\"]*)\"$")
  public void update_book_by_code_not_exists_code(String code) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    code = "656";
    sampleBook.setCode(code);
  }

  @Then("^The response return code (\\d+) and Validation Exception with message for for update code not exists: \"([^\"]*)\"$")
  public void the_response_return_code_and_Validation_Exception_with_message_for_for_update_code_not_exists(
      int codeExpectStatus, String messageException) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeExpectStatus = 500;
    messageException = "Book not found with code: 656";
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
          restTemplate.exchange("http://localhost:8080/books/update/code/b001", HttpMethod.PUT,
              new HttpEntity<>(sampleBook), String.class, "b001");
      assertEquals(500, responseEntity.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(messageException);
    }
  }

  @Given("^The client request DELETE book/delete/id/(\\d+) for id valid$")
  public void the_client_request_DELETE_book_delete_id_for_id_valid(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request delete api: http://localhost:8080/books/delete/id/74");
  }

  @When("^Delete book with id (\\d+)$")
  public void delete_book_with_id(int uid) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    uid = 74;
    sampleBook.setUid(uid);
  }

  @Then("^The response return code (\\d+) and the book with id = (\\d+) not exist in database$")
  public void the_response_return_code_and_the_book_with_id_not_exist_in_database(
      int codeExpectStatus, int uid) throws Throwable {
    codeExpectStatus = 200;
    Book checkBook = iBookQueryService.findById(uid);
    assertEquals("ngoa long", checkBook.getAuthor());
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity =
        restTemplate.exchange("http://localhost:8080/books/delete/id/74", HttpMethod.DELETE,
            new HttpEntity<>(sampleBook), String.class, codeExpectStatus);
    assertEquals(codeExpectStatus, responseEntity.getStatusCodeValue());
    try {
      Book book = iBookQueryService.findById(uid);
    } catch (BookNotFoundException e) {
      thrown.expectMessage("Cannot find book with ID: 74");
    }

  }

  @Given("^The client request DELETE book/delete/id/-(\\d+) for id invad$")
  public void the_client_request_DELETE_book_delete_id_for_id_invad(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request delete api: http://localhost:8080/books/delete/id/-1");
  }

  @When("^Delete book with id invalid: -(\\d+)$")
  public void delete_book_with_id_invalid(int uid) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    uid = -1;
    sampleBook.setUid(uid);
  }

  @Then("^The response return code (\\d+) and return message exception for invalid id: \"([^\"]*)\"$")
  public void the_response_return_code_and_return_message_exception_for_invalid_id(
      int codeExpectedStatus, String messageException) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeExpectedStatus = 500;
    messageException = "Invalid parameter with uid < 0";
    // Write code here that turns the phrase above into concrete actions
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
          restTemplate.exchange("http://localhost:8080/books/delete/id/-1", HttpMethod.DELETE,
              new HttpEntity<>(sampleBook), String.class, -1);
      assertEquals(500, responseEntity.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(messageException);
    }
  }

  @Given("^The client request DELETE book/delete/id/(\\d+) for id not exist$")
  public void the_client_request_DELETE_book_delete_id_for_id_not_exist(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request delete api: http://localhost:8080/books/delete/id/999");
  }

  @When("^Delete book with id not exist: (\\d+)$")
  public void delete_book_with_id_not_exist(int uid) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    uid = 999;
    sampleBook.setUid(uid);
  }

  @Then("^The response return code (\\d+) and return message exception for not existed id: \"([^\"]*)\"$")
  public void the_response_return_code_and_return_message_exception_for_not_existed_id(
      int codeExpectedStatus, String messageException) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    codeExpectedStatus = 500;
    messageException = "Book does not exists!";
    // Write code here that turns the phrase above into concrete actions
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
          restTemplate.exchange("http://localhost:8080/books/delete/id/999", HttpMethod.DELETE,
              new HttpEntity<>(sampleBook), String.class, 999);
      assertEquals(500, responseEntity.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      thrown.expectMessage(messageException);
    }
  }


  @Given("^The client request DELETE book/delete/code/b(\\d+) for code valid$")
  public void the_client_request_DELETE_book_delete_code_b_for_code_valid(int arg1)
      throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request delete api: http://localhost:8080/books/delete/code/b6789");
  }

  @When("^Delete book with code: \"([^\"]*)\"$")
  public void delete_book_with_code(String code) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    code = "b6789";
    sampleBook.setCode(code);
  }

  @Then("^The response return code (\\d+) and the book with code = \"([^\"]*)\" not exist in database$")
  public void the_response_return_code_and_the_book_with_code_not_exist_in_database(
      int codeExpectedStatus, String code) throws Throwable {
    codeExpectedStatus = 200;
    code = "b6789";
    Book checkBook = iBookQueryService.findByCode(code);
    assertEquals("thuy hu", checkBook.getName());
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity =
        restTemplate.exchange("http://localhost:8080/books/delete/code/b6789", HttpMethod.DELETE,
            new HttpEntity<>(sampleBook), String.class, code);
    assertEquals(codeExpectedStatus, responseEntity.getStatusCodeValue());
    try {
      Book book = iBookQueryService.findByCode(code);
    } catch (BookNotFoundException e) {
      thrown.expectMessage("Cannot find book with Code: b6789");
    }
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^The client request DELETE book/delete/code/ for code invalid$")
  public void the_client_request_DELETE_book_delete_code_for_code_invalid() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request delete api: http://localhost:8080/books/delete/code/");
  }

  @When("^Delete book with code is blank$")
  public void delete_book_with_code_is_blank() throws Throwable {
    sampleBook.setCode("");
  }

  @Then("^The response return code (\\d+) and return message exception for code blank: \"([^\"]*)\"$")
  public void the_response_return_code_and_return_message_exception_for_code_blank(
      int codeExpectedStatus, String code) throws Throwable {
    codeExpectedStatus = 500;
    code = "";
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
          restTemplate.exchange("http://localhost:8080/books/delete/code/%20%20", HttpMethod.DELETE,
              new HttpEntity<>(sampleBook), String.class, code);
      assertEquals(codeExpectedStatus, responseEntity.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      // TODO: handle exception
      thrown.expectMessage("Invalid parameter, code is blank");
    }

  }

  @Given("^The client request DELETE book/delete/code/xxx for code not exists$")
  public void the_client_request_DELETE_book_delete_code_xxx_for_code_not_exists()
      throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Request delete api: http://localhost:8080/books/delete/xxx");;
  }


  @When("^Delete book with code not exist: \"([^\"]*)\"$")
  public void delete_book_with_code_not_exist(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^The response return code (\\d+) and return message exception for code not existed: \"([^\"]*)\"$")
  public void the_response_return_code_and_return_message_exception_for_code_not_existed(
      int codeExpectedStatus, String code) throws Throwable {
    codeExpectedStatus = 500;
    code = "xxx";
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
          restTemplate.exchange("http://localhost:8080/books/delete/code/xxx", HttpMethod.DELETE,
              new HttpEntity<>(sampleBook), String.class, code);
      assertEquals(codeExpectedStatus, responseEntity.getStatusCodeValue());
    } catch (HttpServerErrorException e) {
      // TODO: handle exception
      thrown.expectMessage("Book does not exists!");
    }
  }

}
