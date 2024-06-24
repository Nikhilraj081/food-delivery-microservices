# Food Delivery Microservices

Welcome to the Food Delivery Microservices project, a scalable and efficient backend solution for a food delivery application. This project is developed using Spring Boot and MongoDB Atlas, following the microservices architecture to ensure modularity, flexibility, and ease of maintenance.

## Features

- **User Authentication**: Secure user registration and login.
- **Restaurant Management**: CRUD operations for restaurant data.
- **Order Management**: Handle order placement, updates, and tracking.
- **Cart Management**: Manage user cart operations.
- **Service Communication**: Efficient communication between microservices.

## Tech Stack

### Backend
- **Spring Boot**: Framework for building microservices.
- **Spring Security**: For authentication and authorization.
- **Spring Data MongoDB**: For data persistence.
- **MongoDB Atlas**: Fully managed cloud database service.

### Deployment
- **AWS EC2**: Microservices are deployed on Amazon EC2 instances.
- **Spring Cloud**: For microservices communication and configuration.

## Microservices

1. **Auth Service**: Manages user authentication and authorization.
2. **Restaurant Service**: Manages restaurant data.
3. **Order Service**: Manages orders.
4. **Cart Service**: Manages user carts.

## Getting Started

### Prerequisites
- **Java** (JDK 17)
- **Maven** (3.8.4): For building the project.

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Nikhilraj081/food-delivery-microservices.git
   cd food-delivery-microservices
  

#### Build and Run each Microservice:
Navigate to each microservice directory and run:

```bash
mvn clean install
mvn spring-boot:run


/registry-service
/api-gateway
/authorization-service
/restaurants-service
/cart-service
/order-service
```

Once  all server is started paste below link into your browser to check status of services, if all service is UP then we are good to test endpoins.
```bash
  http://localhost:8761/
```
![alt text](</readmeImage/Screenshot (64).png>)

## Api test

#### Postman collection test:
To test with postman collection go to postman folder and import collection and environment in your postman workspace.

```bash
 collction file location: food-delivery-microservices/postman/Collection/
 environment file location: food-delivery-microservices/postman/Environment/

```
![alt text](</readmeImage/Screenshot (70).jpg>)

Once environment and collections are imported go to auth-service/Login and hit api to gererate auth token.

![alt text](</readmeImage/Screenshot (65).png>)

Once token is generated copy that token and go to environment "Testing" and change token value with new generated token value.

![alt text](</readmeImage/Screenshot (71).jpg>)

 After that select environment (Testing) in postman, Now we are good to run collection.

 ![alt text](</readmeImage/Screenshot (67).jpg>)
