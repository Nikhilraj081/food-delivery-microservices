FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY authorization-service/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8081
