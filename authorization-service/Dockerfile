FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY food-delivery-microservices/authorization-service/jar/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8081
