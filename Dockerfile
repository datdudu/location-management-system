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

# Copy the application JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Default CMD when no arguments are passed to the container
CMD ["java", "-jar", "/app.jar"]
