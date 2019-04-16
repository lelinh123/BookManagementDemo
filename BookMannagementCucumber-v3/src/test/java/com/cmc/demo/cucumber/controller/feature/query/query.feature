@book_query_controller
Feature: Book API related features

  @book_findAll
  Scenario: Get all book record in database

 	When The client request GET /book api
 			
    Then The response return a lists of book record in database and status code 200;
    
  @book_findById
  Scenario: Get a book record in database by id
  	When The client request GET find by id api
  	
    Then The response return a  book record by id in database and status code 200;
    
 @book_findByIdInvalid
  Scenario: Get a book record in database by id invalid
  	When The client request GET find by id less zero
  	
    Then The response return HttpStatuscode is 500 and ValidationException;
    
  @book_findByCode
  Scenario: Get a book record in database by code
  	When The client request GET find by code api
  	Then The response return a  book record by code in database and status code 200;
  	
  	
  @book_findByCodeInvalid
  Scenario: Get a book record in database by code invalid
  	When The client request GET find by code is blank
  	
    Then The response return HttpStatuscode is 500 and ValidationException  with messsage Code is blank;
    
  @book_findByName
  Scenario: Get list of book record in database by name
  	When The client request GET find by name api
  	
    Then The response return a list  book record by name in database and status code 200;
  
  
  @book_findByNameInvalid
  Scenario: Get a book record in database by name invalid
  	When The client request GET find by name is blank
  	
    Then The response return HttpStatuscode is 500 and ValidationException Name is blank;
    
      
  @book_findByCategory
  Scenario: Get list of book record in database by category
  	When The client request GET find by category api
  	
    Then The response return a list  book record by category in database and status code 200;
  
  
  @book_findByCategoryInvalid
  Scenario: Get a book record in database by category invalid
  	When The client request GET find by category is blank
  	
    Then The response return HttpStatuscode is 500 and ValidationException Category is blank;  
    
  @book_findByAuthor
  Scenario: Get list of book record in database by author
  	When The client request GET find by author api
  	
    Then The response return a list  book record by author in database and status code 200;
    
 @book_findByAuthorInvalid
  Scenario: Get a book record in database by author invalid
  	When The client request GET find by author is blank
  	
    Then The response return HttpStatuscode is 500 and ValidationException Author is blank;
    
