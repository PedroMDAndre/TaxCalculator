## TaxCalculator
Value Added Tax Calculator


## User story 
As an API user, I would like to calculate Net, Gross, VAT amounts for purchases in Austria so  that I can use correctly calculated purchase data. 

## ACCEPTANCE CRITERIA 
* If the API receives one of the net, gross or VAT amounts and additionally a valid  Austrian VAT rate (10%, 13%, 20%), the other two missing amounts  (net/gross/VAT) are calculated by the system and returned to the client in a  meaningful structure 
* The API provides an error with meaningful error messages, in case of:
  - missing or invalid (0 or non-numeric) amount input 
  - more than one input 
  - missing or invalid (0 or non-numeric) VAT rate input 


## TECHNICAL REQUIREMENTS 
* the solution needs to be implemented in Java 17 or above 
* the solution needs to be based on Spring framework (Jersey framework can also be  used for the REST endpoint) 
* the API must fulfil the REST API standards 
* the application needs to based on Maven or Gradle 


Link used to test the calculation logic: 
http://www.calkoo.com/?lang=3&page=8


## Frontend
http://localhost:4200/

## API documentation
http://localhost:8080/v3/api-docs/

## Swagger UI
http://localhost:8080/ <br>
http://localhost:8080/swagger-ui/index.html
