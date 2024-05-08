
# Food-Delivery-Microservices

The Food Delivery Microservices project aims to develop a scalable and efficient system for managing food delivery operations. Leveraging the microservices architecture with Spring Boot and MongoDB, the project ensures modularity, flexibility, and robustness in handling various aspects of the food delivery process.

## Requirements
    java 17
    maven 3.8.4

## Installation and Run

Clone this repository in your local system

```bash
  git clone https://github.com/Nikhilraj081/food-delivery-microservices.git
```
Open terminal and go to food-delivery-microservices folders and then go to below folder one by one:
```bash
/registry-service/registry-service
/ApiGeteway/ApiGeteway
/authorization-service/authorization-service
/food-menu-service/food-menu-service
/cart-service/cart-service/
/order-service/order-service
```
And run below command to start each service

```bash
  mvn clean spring-boot:run
```
Once  all server is started paste below link to your browser to check status of services, if all service is UP then we are good to test endpoins.
```bash
  http://localhost:8761/
```
![alt text](</ReadmeImage/Screenshot (64).png>)

## Api test

#### Postman collection test:
To test with postman collection go to Postman/Collection folder and import collection and environment in postman workspace.

```bash
 collction file location: food-delivery-microservices/Postman/Collection/
 environment file location: food-delivery-microservices/Postman/Environment/

```
Once environment and collections are imported go to auth-service/Login api and gererate token 

![alt text](</ReadmeImage/Screenshot (65).png>)

Once token is generated copy that token and go to environment "Testing" and change token value with new generated token value

![alt text](</ReadmeImage/Screenshot (66).png>)

 After that select environment (Testing) in postman, Now we are good to run collection.

 ![alt text](</ReadmeImage/Screenshot (67).png>)
