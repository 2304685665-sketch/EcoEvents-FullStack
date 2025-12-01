# EcoEvents – Full-Stack Volunteer & Event Management Platform

EcoEvents is a full-stack web application that helps small environmental charities publish events and manage volunteer sign-ups without needing a dedicated tech team.

> This project was designed and implemented from scratch as a self-directed learning project, not as a tutorial or classroom assignment.

## Features

- Create, update and archive events with date, capacity and location
- Volunteers can view events and register or cancel their place
- Many-to-many relation between events and volunteers in the database
- Server-side validation for required fields, dates and capacity
- Simple filters for upcoming and past events
- Basic HTML and Thymeleaf UI so admins can manage events without using SQL

## Tech stack

- Backend: Java 17, Spring Boot (MVC)
- Frontend: HTML5, CSS3, Thymeleaf
- Database: MySQL (relational)
- Persistence: Spring Data JPA / Hibernate



###  Testing

I wrote unit tests for the service layer using **JUnit 5**, **Mockito**, and **AssertJ**.

- Mocked the JPA repository in `EventServiceTest` so that the service logic can be verified without touching the real database.
- Covered both **happy-path** and **error-handling** scenarios, including:
    - returning all events from the repository,
    - fetching a single event by ID,
    - throwing a `ResourceNotFoundException` when an event cannot be found,
    - saving a new event and returning the persisted entity.
- This makes the core business logic easier to maintain and safer to change in the future.

###  How to run the tests

From the project root:

```bash
mvn test

###  Key Features
* **Event Showcase:** Dynamic display of upcoming events fetched from the database.
* **Volunteer Registration:** Secure form submission handling (POST requests).
* **Data Persistence:** Fully functional MySQL integration with normalized tables (Events, Volunteers, Registrations).
* **Responsive UI:** Clean, card-based interface inspired by modern charity websites.

###  How to Run
1. Clone the repository.
2. Configure your MySQL username and password in `application.properties`.
3. Run `EcoeventsApplication.java`.
4. Access via `http://localhost:8080`.

##  REST API layer

On top of the server-side rendered HTML pages, I also exposed a small REST API
so that a separate frontend or mobile client could consume the core data.

### Endpoints

- `GET /api/v1/events`  
  Returns a JSON array of all events.

- `GET /api/v1/events/{id}`  
  Returns a single event by id, or a `404` JSON error if the event does not exist.

Example success response:

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
