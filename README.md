# Waste Sorting App

## Description

The **Waste Sorting App** is a RESTful web application designed to promote proper waste management and recycling by providing users with comprehensive disposal guidelines and categorization. This application simplifies waste sorting and encourages environmentally friendly habits.

## Features

- **Disposal Guidelines**: View, create, update, and delete disposal guidelines for different waste types.
- **Waste Categories**: Manage waste categories and their properties such as recyclability.
- **Category-Based Guidelines**: Fetch disposal guidelines for specific waste categories.
- **Recycling Tips**: Provides general tips to improve recycling efficiency.
- **RESTful API**: Fully implemented RESTful API for integration with other systems or front-end applications.
- **Validation**: Ensures data consistency with robust validation rules.

## Technologies Used

- **Java**: Application logic and API development.
- **Spring Boot**: For rapid application development and RESTful API implementation.
- **H2 Database**: Lightweight in-memory database for development and testing.
- **Maven**: Dependency management and build automation.
- **JUnit 5**: For writing unit and integration tests.
- **Mockito**: Mocking framework for test isolation.

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven 3.6 or higher
- IDE (e.g., IntelliJ IDEA or Eclipse)
- Postman (optional, for testing API endpoints)

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/ray-cod/waste-sorting-app.git
   cd waste-sorting-app

2. **Build the Application**:
   ```bash
   mvn clean install

3. **Run the Application**:
   ```bash
   mvn spring-boot:run

4. **Access the API**:

- Base URL: http://localhost:8080/api

## API Endpoints

### Disposal Guidelines

- **Get All Guidelines**: GET /api/disposal-guidelines
- **Create Guideline**: POST /api/disposal-guidelines
- **Update Guideline**: PUT /api/disposal-guidelines/{id}
- **Delete Guideline**: DELETE /api/disposal-guidelines/{id}
- **Get Guidelines by Category**: GET /api/disposal-guidelines/category/{wasteCategoryId}

### Waste Categories

- **Get All Categories**: GET /api/waste-categories
- **Create Category**: POST /api/waste-categories
- **Update Category**: PUT /api/waste-categories/{id}
- **Delete Category**: DELETE /api/waste-categories/{id}
- **Get Category by Name**: Get /api/waste-categories/search?name=...

### Recycling Tips

- **Get All Tips**: GET /api/recycling-tips
- **Create Tips**: POST /api/recycling-tips
- **Update Tips**: PUT /api/recycling-tips/{id}
- **Delete Tips**: DELETE /api/recycling-tips/{id}
- **Get Tips by Material**: Get /api/recycling-tips/material/{material}
