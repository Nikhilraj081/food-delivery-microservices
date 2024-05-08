
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
Once  all server is started paste below link into your browser to check status of services, if all service is UP then we are good to test endpoins.
```bash
  http://localhost:8761/
```
![alt text](<Screenshot (64).png>)

## Api test

