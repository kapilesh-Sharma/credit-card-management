# credit-card-management

Credit card Spring boot based microservice, Restful endpoint implemented to expose Add, Update and fetch all Credit card records.

## Service Metadata
* **Service Name:** api/v1/credit-cards
* **Version:** 1

## Getting Started
In order to run the application post checkout:
Must have - 
* Maven 3.3 or later
* JDK 8.0 or later
* Bash command prompt or equivalent 

### Clone
To get started please clone and checkout this repository using git:
https://github.com/kapilesh-Sharma/credit-card-management.git

### API Endpoints running application locally 
From command prompt run -
        
         mvn spring-boot:run

The above command will run Spring boot application in its default tomcat container


## API Endpoints
Application has Spring Basic-Authentication security configured so apart from Health check URL all other Resource endpoint are secured and should provide valid - Basic Auth **Username and Password**.
* **Health check** - http://localhost:8080/actuator/health
* Functionality - To check if API is up and running 

* **Security Configuration** 

    Type - Basic auth                                                                                                                                                                                                               
    User name - **user**                                        
    Password - **password**
    
    CURL code for the reference -   
    curl --location --request GET 'http://localhost:8080/api/v1/credit-cards' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
    --header 'Cookie: JSESSIONID=9890972B935C65880000040F527D3074'

* **GET** - http://localhost:8080/api/v1/credit-cards
* Functionality - Display all the available credit card records in the database
* Response - HTTP status code **200**

* **POST** - http://localhost:8080/api/v1/credit-cards
* Functionality - Create a credit card record in the system
* Response - HTTP status code **200**
* Request Json sample -

        {
            "name": "john",
            "cardNumber": "4111111111111111",
            "balance": "0",
            "limit": "4000"
        }
      

* **PUT** - http://localhost:8080/api/v1/credit-cards/4111111111111111
* Functionality - Update existing credit card record based on provided card number
* Response - HTTP status code **200**
* Request Json sample -

       {
            "name": "john",
            "cardNumber": "4111111111111111",
            "balance": "0",
            "limit": "2000"
        }
        

## Validation Errors
Based on input and data validation either request will get proceed successfully 
or Exception will thrown with details:
* Example : In case of balance value other then zero
* Http status **400** & **500**
* Body -

        {
            "uri": "/api/v1/credit-cards",
            "exceptionMessage": "Intial Balance should be zero"
        }
