# Fabrikza

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://openjdk.org/)

> A Spring Boot application for [brief description]

## 🚀 Features

- RESTful API with Spring Boot
- JWT Authentication
- JPA/Hibernate for data persistence
- Input validation
- Swagger API documentation

## 🛠️ Tech Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Security**
- **PostgreSQL/MySQL**
- **Maven**

## 📋 Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL/MySQL

## 🔧 Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/KDDilshan/Fabrikza.git
   cd Fabrikza
   ```

2. **Configure database**
   ```bash
   # Update src/main/resources/application.properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/fabrikza_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

## 📁 Project Structure

```
src/
├── main/java/com/example/fabrikza/
│   ├── FabrikzaApplication.java
│   ├── controller/        # REST controllers
│   ├── service/          # Business logic
│   ├── repository/       # Data repositories
│   └── entity/           # JPA entities
└── main/resources/
    └── application.properties
```

## 🔌 API Endpoints

```http
GET    /api/items          # Get all items
GET    /api/items/{id}     # Get item by ID
POST   /api/items          # Create new item
PUT    /api/items/{id}     # Update item
DELETE /api/items/{id}     # Delete item
```

## 🧪 Testing

```bash
mvn test
```

## 🐳 Docker

```bash
mvn clean package
docker build -t fabrikza .
docker run -p 8080:8080 fabrikza
```

## 👤 Author

**KD Dilshan** - [@KDDilshan](https://github.com/KDDilshan)

## 📄 License

MIT License - see [LICENSE](LICENSE) file for details.
