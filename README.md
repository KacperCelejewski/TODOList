# 📝 To-Do List Application

A simple To-Do List web application built with **Java**, **Spring Boot**, **Maven**, and **Hibernate**.  
It provides a REST API with basic **CRUD** operations for managing tasks.

---

## 🚀 Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- Maven
- H2 (in-memory database for development/testing)


---

## 📦 Setup & Run

### Prerequisites
- Java 17 or later
- Maven

### 1. Clone the repository

```bash
git clone https://github.com/KacperCelejewski/TODOList.git
```

### 2. Build the project

```bash
mvn clean install
```

### 3. Run the application

```bash
mvn spring-boot:run
```

The app will be available at:  
📍 `http://localhost:8080`

---

## 📚 REST API Endpoints

| Method | Endpoint           | Description            |
|--------|--------------------|------------------------|
| GET    | `/api/tasks`                   | Get all to-do items    |
| POST   | `/api/tasks/create-task`       | Create a new task      |
| PUT    | `/api/tasks/update/{id}`       | Update an existing task by ID |
| DELETE | `/api/tasks/delete{id}`        | Delete a task by ID    |

---

## 🧪 Example JSON Payload

### 🔸 Create / Update Task

```json
{
  "title": "Buy groceries",
  "completed": false,
  "content": "Milk, Bread, Eggs"
}
```

---

## 🗂 Project Structure

```
src/
 └── main/
     ├── java/
     │    └── com.example.todolist/
     │         ├── controller/
     │         ├── dto/
     │         ├── entities/
     │         ├── repositories/
     │         ├── services/
     │         ├── exception/
     │         └── TodoApplication.java
     └── resources/
          ├── application.properties

```

---

## ⚙️ Example Configuration (`application.properties`)

```properties
# H2 Database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

# Enable H2 Console
spring.h2.console.enabled=true

# Hibernate debug
spring.jpa.show-sql=true
```

Access H2 console at: `http://localhost:8080/h2-console`

---

## 🛠 Features

- ✅ Add new tasks
- 📋 View all tasks
- ✏️ Edit/update tasks
- ❌ Delete tasks
- 🔁 RESTful design

---

## 📌 Possible Enhancements

- User authentication & roles
- Task due dates and reminders
- Docker support and deployment

---

## 📄 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author

[Kacper Celejewski]  
Feel free to contribute, fork, or suggest improvements!
