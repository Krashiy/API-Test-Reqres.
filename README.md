# Project Description

In this project, I tested the API on the platform "reqres.in". A total of six test cases were tested, such as field matching, sorting check, successful and unsuccessful registration, user renewal and deletion.

## Modules

  - Selenium
  - JUnit
  - RestAssured
  - Jackson Databind
  
## Documentation

  ### api package
    This package holds API tests with and without the use of Pojo classes. There is also a specification for configuring the query and response.
    - ApiTest
    - ApiTestWithoutPojo
    - Specification
    
  ### pojoClasses package
    Here are all the classes that describe the queries and responses to the API.
    - RegisterData
    - ResourceData
    - SuccessRegister
    - SuccessUpdateUser
    - UnSuccessRegister
    - UpdateUser
    - UserData
    
  ### helpers package
    Stores a class with URL path for working with the API.
    - Data
