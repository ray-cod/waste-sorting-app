# -----------------------------------------------------------------------------
# Dockerfile for Waste Sorting Spring Boot API
#
# This image runs a Java 17 Spring Boot application using a fat JAR.
# Base Image: eclipse-temurin:17-jdk-jammy
# Exposes: Port 8080
# Author: Raimi Dikamona Lassissi
# Version: 1.0.0
# -----------------------------------------------------------------------------

#Base image
FROM eclipse-temurin:17-jdk-jammy

#Metadata
LABEL maintainer="raimilassissi8@gmail.com"
LABEL version="1.0.0"
LABEL description="Spring Boot Waste Sorting API application packaged as a JAR"

#Set working directory & copy
WORKDIR /app
COPY target/waste-sorting-app-0.0.1-SNAPSHOT.jar app.jar

#Expose port
EXPOSE 8080

#Default command
CMD ["java", "-jar", "app.jar"]