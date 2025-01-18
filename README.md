# 🌐 University Class Scheduling API (Spring Boot + PostgreSQL)

## 📖 Description
This project is a RESTful API for scheduling university classes, built with **Spring Boot** and **PostgreSQL**. 

- **Coordinators** can manage and create everything in the system, including courses, schedules, rooms, reservations and more.
- **Professors** can only create reservations for their classes and manage them.
- **Students** can only **view** schedules and reservations for classes.

The API includes **JWT-based authentication**, **role-based security** (for professors, coordinators, and students), and interactive API documentation using **Swagger**. Unit tests are written using **JUnit** and **Mockito**.


### 🚀 Features
- **🔒 JWT Authentication**: Secure login and registration using JWT tokens.
- **🔑 Role-Based Security**: Access control based on roles: **Professor**, **Coordinator**, and **Student**.
- **📅 Class Scheduling**: Manage class schedules and reservations.
- **📂 CRUD Operations**: Create, read, update, and delete operations for subjects, schedules, rooms, course, reservations and more.
- **📊 Swagger Documentation**: Interactive API documentation to explore and test the endpoints.
- **🧪 Unit Testing**: Comprehensive unit tests using **JUnit** and **Mockito** to ensure code reliability.
- **🗃 PostgreSQL**: Data storage for schedules, courses, and other related information.

## 🛠️ Tech Stack

- **Spring Boot**: Java-based framework for building the backend API.
- **PostgreSQL**: Relational database for storing scheduling data.
- **JWT**: JSON Web Token for secure user authentication.
- **Swagger**: Interactive API documentation for easy exploration.
- **Docker**: Containerization for easy deployment and scalability.
- **JUnit**: For unit testing and ensuring code quality.
- **Mockito**: For mocking dependencies in unit tests.
- **Maven**: Dependency management and build automation tool for Java projects.

## 🚀 Deployment
The backend is deployed on **Render**, a platform for hosting web applications. The API is publicly accessible via the Render domain.

🤝 Contributors
Special thanks to the contributors:
@nicksvalle

Feel free to contribute by opening issues or submitting pull requests on the GitHub Repository.
