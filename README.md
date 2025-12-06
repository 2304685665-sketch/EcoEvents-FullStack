# EcoEvents – Full-Stack Volunteer & Event Management Platform

EcoEvents is a full-stack web application that helps small environmental charities publish events and manage volunteer sign-ups without needing a dedicated tech team.

> This project was designed and implemented from scratch as a self-directed learning project.

## Features

- **Event Showcase:** Dynamic display of upcoming events fetched from the database.
- **Volunteer Registration:** Secure form submission handling for user sign-ups.
- **Data Persistence:** Fully functional MySQL integration with normalized tables (Events, Volunteers, Registrations).
- **Responsive UI:** Clean, card-based interface inspired by modern charity websites.
- **Server-side Validation:** Checks for required fields, valid dates, and capacity limits.

## Tech Stack

- **Backend:** Java 17, Spring Boot (MVC)
- **Frontend:** HTML5, CSS3, Thymeleaf
- **Database:** MySQL (Relational)
- **Persistence:** Spring Data JPA / Hibernate

## REST API Documentation

The application provides a RESTful API documented via **SpringDoc OpenAPI (Swagger UI)**. This allows for real-time testing and visualization of all endpoints, including Event Management and Volunteer Registration modules.

![Swagger UI Screenshot](docs/api-docs.png)

### API Highlights

- **Standardization:** The API follows REST conventions and uses standard HTTP status codes.
- **Operations:** Supports retrieval of event lists, detailed views, and registration handling.
- **Response Format:** All endpoints return standardized JSON data.

**Example Response:**

```json
[
  {
    "id": 1,
    "title": "Spring Park Clean-Up",
    "description": "Join us to clean up East London Waterworks Park! Gloves provided.",
    "eventDate": "2026-03-15",
    "location": "East London Waterworks Park"
  }
]

## Testing & Reliability

I implemented comprehensive unit tests for the Service Layer using **JUnit 5** and **Mockito** to ensure business logic stability.

- **Coverage:** Achieved **100% line coverage** on core service classes (verified via JaCoCo).
- **Scope:** Validated happy paths, edge cases, and exception handling (e.g., `ResourceNotFoundException`).

![Service Layer Coverage](docs/test-coverage.png)

### Run Tests

```bash
mvn test

### How to Run
Clone the repository.

Configure your MySQL username and password in application.properties.

Run EcoeventsApplication.java.

Access the application via http://localhost:8080.

Access the API documentation via http://localhost:8080/swagger-ui.html.
