# Use Maven 3.8.5 with OpenJDK 17 for building the application
FROM maven:3.8.5-openjdk-17-slim AS build
#FROM openjdk:17-jdk-slim-buster

# Set the working directory
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and package the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use OpenJDK 17 JDK Alpine as the runtime image
FROM openjdk:17-jdk-alpine

# Install curl and bash (required for dockerize)
RUN apk add --no-cache curl bash

# Set environment variables
ENV DOCKERIZE_VERSION v0.6.1

# Download and install dockerize
RUN curl -fSL https://github.com/jwilder/dockerize/releases/download/${DOCKERIZE_VERSION}/dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz \
    | tar xz -C /usr/local/bin

# Copy the application JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Entrypoint command to run the application with dockerize
ENTRYPOINT ["dockerize", "-wait", "tcp://elasticsearch:9200", "-timeout", "60s", "--"]

# Default CMD when no arguments are passed to the container
CMD ["java", "-jar", "/app.jar"]
