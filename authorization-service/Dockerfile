
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Install necessary packages
RUN apt-get update && \
    apt-get install -y wget && \
    apt-get clean
VOLUME /tmp
#copy jar from s3 bucket
RUN wget -O app.jar https://food-delivery-app-bucket.s3.ap-south-1.amazonaws.com/Java-App/authorization-service-v3.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8081
