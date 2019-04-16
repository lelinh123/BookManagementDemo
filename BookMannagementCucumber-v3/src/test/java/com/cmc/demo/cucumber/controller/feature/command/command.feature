
Feature: Book Command API related features
  
  @book_create
  Scenario: Create a new book record in database 
  	Given The client request POST book/create
  	
  	When  book detail: uid: 100, code: "b0412",name: "thuy hu", description: "tong giang truyen", category: "tieu thuyet tien hiep",  author: "ngoa long", publisher: "nxb lao dong",  createUser: "lhlinh", createDate: "25/07/1991", updateUser: "lhlinh", updateDate: "25/07/1991" 
  	  
    Then The response return code 201 and the new book exist in database
	
  @book_create_IdInvalid
  Scenario: Create a new book with id invalid
  	Given The client request POST book/create with id invalid
  	
  	When  create book with id invalid: uid: -1, code: "b0416",name: "thuy hu", description: "tong giang truyen", category: "tieu thuyet tien hiep",  author: "ngoa long", publisher: "nxb lao dong",  createUser: "lhlinh", createDate: "25/07/1991", updateUser: "lhlinh", updateDate: "25/07/1991" 
  	  
    Then The response return code 500 and Validation Exception with message for uid invalid: "Invalid parameter with uid: -1"	
    
    @book_create_IdInvalid
  Scenario: Create a new book with id existed
  	Given The client request POST book/create with id existed
  	
  	When  create book with id existed: uid: 1, code: "b0417",name: "thuy hu", description: "tong giang truyen", category: "tieu thuyet tien hiep",  author: "ngoa long", publisher: "nxb lao dong",  createUser: "lhlinh", createDate: "25/07/1991", updateUser: "lhlinh", updateDate: "25/07/1991" 
  	  
    Then The response return code 500 and Validation Exception with message for uid existed: "Uid is existed"
	
   @book_create_CodeExisted
   Scenario: Create a new book with code was exist 
  	Given The client request POST book/create with code was exist 
  	 
  	When  create book with code was exist : uid: 100, code: "b0412",name: "thuy hu", description: "tong giang truyen", category: "tieu thuyet tien hiep",  author: "ngoa long", publisher: "nxb lao dong",  createUser: "lhlinh", createDate: "25/07/1991", updateUser: "lhlinh", updateDate: "25/07/1991" 
  	  
    Then The response return code 500 and Validation Exception with message for code exited: "Code is existed"
    
   @book_create_CodeBlank
   Scenario: Create a new book with code is blank
  	Given The client request POST book/create with code is blank
  	
  	When  create book code is blank: uid: 101, code: " ",name: "thuy hu", description: "tong giang truyen", category: "tieu thuyet tien hiep",  author: "ngoa long", publisher: "nxb lao dong",  createUser: "lhlinh", createDate: "25/07/1991", updateUser: "lhlinh", updateDate: "25/07/1991" 
  	  
    Then The response return code 500 and Validation Exception with message for code is blank : "Code is blank"
    
   @book_create_FieldOtherBlank
   Scenario: Create a new book with field other is blank
  	Given The client request POST book/create with field other is blank
  	
  	When  create book with field other is blank: uid: 101, code: "b3456",name: "", description: "tong giang truyen", category: "tieu thuyet tien hiep",  author: "ngoa long", publisher: "nxb lao dong",  createUser: "lhlinh", createDate: "25/07/1991", updateUser: "lhlinh", updateDate: "25/07/1991" 
  	  
    Then The response return code 500 and Validation Exception with message for other blank: "Invalid parameter. Field can't blank"

    
   @book_update_by_id
   Scenario: Update book with ui
   	Given The client request POST book/update valid
  	When  update book with uid: 1, modify name: "Hong Lau Mong"
    Then The response return code 200 and name of book with id 1 id modified to "Hong Lau Mong"  in database
    
     
   @book_update_by_id_invalid
   Scenario: Update book with uid invalid
  	Given The client request POST book/update/id with id invalid 
  	
  	When  update book with uid invalid: uid = -1
  	  
    Then The response return code 500 and Validation Exception with message for uid invalid less than zero : "Invalid parameter with uid < 0"
    
    @book_update_by_id_have_code_blank
   	Scenario: Update book by id have code invalid
  	Given The client request POST book/update/id with code invalid
  	
  	When  update book by id have code invalid: id = 1, code = ""
  	  
    Then The response return code 500 and Validation Exception with message for code invalid : "Code is blank "
    
    @book_update_by_id_have_field_blank
    Scenario: Update book by id have name blank
  	Given The client request POST book/update/id with field name invalid
  	
  	When  update book by id have name blank: id = 1, name = ""
  	  
    Then The response return code 500 and Validation Exception with message for filed name blank : "Invalid parameter. Field can't blank "
    
    @book_update_by_id_not_exists
    Scenario: Update book by id not exists
  	Given The client request POST book/update/id with id not exists
  	
  	When  update book id not exist: id = 656
  	  
    Then The response return code 500 and Validation Exception with message for id not exists : "Book not found with uid: 656"

    @book_update_by_code
    Scenario: Update book by code valid
   	Given The client request POST book/update/code code valid
  	When  update book with code: "b001", modify name: "Tam quoc"
    Then The response return code 200 and name of book had modified to in database
    
     
   @book_update_by_code_invalid
   Scenario: Update book by code invalid
  	Given The client request POST book/update/code for Update book by code invalid
  	
  	When  update book Update book by code is blank: code is blank
  	  
    Then The response return code 500 and Validation Exception with message for update book by code invalid  : "Invalid parameter with code is blank"
    
    
    
    @book_update_by_code_have_field_blank
    Scenario: Update book by code have name blank
  	Given The client request POST book/update/code for update by code have name blank
  	
  	When  update book by code have name blank: code = "b001", name = ""
  	  
    Then The response return code 500 and Validation Exception with message for update by code have name blank : "Invalid parameter. Field can't blank "
    
    
    @book_update_by_code_not_exists
    Scenario: Update book by code not exists
  	Given The client request POST book/update/code for update code not exists
  	
  	When  update book by code not exists: code = "656"
  	  
    Then The response return code 500 and Validation Exception with message for for update code not exists: "Book not found with code: 656"
    
    @book_delete_by_id
  	Scenario: Delete a book record in database by id 
  	Given The client request DELETE book/delete/id/74 for id valid
  	
  	When  Delete book with id 74
  	 
    Then The response return code 200 and the book with id = 74 not exist in database
    
    @book_delete_by_id_invalid
  	Scenario: Delete a book record in database by id invalid
  	Given The client request DELETE book/delete/id/-1 for id invad
  	
  	When  Delete book with id invalid: -1
  	 
    Then The response return code 500 and return message exception for invalid id: "Invalid parameter with uid : -1"
    
    @book_delete_by_id_not_exist
  	Scenario: Delete a book record in database by id not existed
  	Given The client request DELETE book/delete/id/999 for id not exist
  	
  	When  Delete book with id not exist: 999
  	 
    Then The response return code 500 and return message exception for not existed id: "Book does not exists!"

   @book_delete_by_code
  	Scenario: Delete a book record in database by code 
  	Given The client request DELETE book/delete/code/b0412 for code valid
  	
  	When  Delete book with code: "b0412"
  	 
    Then The response return code 200 and the book with code = "b0412" not exist in database
    
    @book_delete_by_code_invalid
  	Scenario: Delete a book record in database by code is blank
  	Given The client request DELETE book/delete/code/ for code invalid
  	
  	When  Delete book with code is blank
  	 
    Then The response return code 500 and return message exception for code blank: "Invalid parameter, code is blank"
    
    @book_delete_by_code_not_exist
  	Scenario: Delete a book record in database by id not existed
  	Given The client request DELETE book/delete/code/xxx for code not exists
  	
  	When  Delete book with code not exist: "xxx"
  	 
    Then The response return code 500 and return message exception for code not existed: "Book does not exists!"