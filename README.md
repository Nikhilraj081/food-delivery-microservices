
# Food-Delivery-Microservices

The Food Delivery Microservices project aims to develop a scalable and efficient system for managing food delivery operations. Leveraging the microservices architecture with Spring Boot and MongoDB, the project ensures modularity, flexibility, and robustness in handling various aspects of the food delivery process.

**Auth-Service:** Handles user authentication, registration, profile management, and authorization.

**Cart-Service:** Users can add items, remove items, adjust the quantity of items, and view the contents of their cart at any time, including the total price.

**Food-Menu-Service:** Manages foodItem, availability, and orders.

**Order-Service:** Facilitates order placement, tracking, and status updates.

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
/api-gateway/api-gateway
/authorization-service/authorization-service
/restaurants-service
/cart-service/cart-service/
/order-service/order-service
```
And run below command to start each service individually.

```bash
  mvn clean spring-boot:run
```
Once  all server is started paste below link into your browser to check status of services, if all service is UP then we are good to test endpoins.
```bash
  http://localhost:8761/
```
![alt text](</readmeImage/Screenshot (64).png>)

## Api test

#### Postman collection test:
To test with postman collection go to postman folder and import collection and environment in postman workspace.

```bash
 collction file location: food-delivery-microservices/postman/Collection/
 environment file location: food-delivery-microservices/postman/Environment/

```
![alt text](</readmeImage/Screenshot (70).jpg>)

Once environment and collections are imported go to auth-service/Login api and gererate token.

![alt text](</readmeImage/Screenshot (65).png>)

Once token is generated copy that token and go to environment "Testing" and change token value with new generated token value.

![alt text](</readmeImage/Screenshot (71).jpg>)

 After that select environment (Testing) in postman, Now we are good to run collection.

 ![alt text](</readmeImage/Screenshot (67).jpg>)
