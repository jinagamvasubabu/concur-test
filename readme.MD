### Tech Stack:
* Spring Boot
* Spring REST
* Spring Data
* MYSQL (for persistence and could not connect to AWS instance due to firewall issue)
* Hikari Connection pooling
* Lombok

### How to Run?
* Checkout the project in your local environment.
* Change the `Datasource.url` in `application.properties` url to the one you wanted to point to
* Run `ApplicationRunner` file to start the application

### Endpoints:
 ## Save :
   * URL: `http://localhost:8282/register/user/save`
   * Method: `POST`
   * Request Object: `Person (id=1, name='concur')`
  
 ## Get Person:
   * URL: `http://localhost:8282/register/get/1` 
   * Method: `GET`
  
 ### Get All Persons
    * URL: `http://localhost:8282/register/get/all` 
    * Method: `GET`
    
    
Please give me a shout @ `jinagamvasubabu@gmail.com` for any doubts or concerns.     
        
