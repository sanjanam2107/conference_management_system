# Conference Management System

A Spring Boot application for managing conferences, sessions, speakers, and attendees.

## Features

- Session Management (Create, Read, Update, Delete)
- Speaker Management
- Attendee Registration
- Different types of sessions (Workshop, Keynote, Panel)
- Implementation of various design patterns:
  - Factory Pattern for session creation
  - Observer Pattern for notifications
  - Strategy Pattern for payment processing
  - Singleton Pattern for configuration

## Technologies Used

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- Thymeleaf
- Bootstrap 5
- Maven

## Getting Started

### Prerequisites

- JDK 17 or later
- Maven 3.6 or later

### Running the Application

1. Clone the repository:
```bash
git clone [repository-url]
cd conference-management
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

4. Access the application at `http://localhost:8080`

## Database

The application uses H2 in-memory database. You can access the H2 console at:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:conferencedb`
- Username: `sa`
- Password: (empty)

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request 