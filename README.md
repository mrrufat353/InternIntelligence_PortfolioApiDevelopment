# 🚀 Portfolio Management API

The **Portfolio Management API** is a secure RESTful service that allows users to create, update, and manage their professional portfolios. It supports features like adding skills, education, projects, and work experiences while ensuring secure access through **JWT Authentication**.

---

## 📖 Overview
This API enables users to:
- ✅ **Create, update, delete, and retrieve portfolios.**
- ✅ **Add skills, education, projects, and experiences to a portfolio.**
- ✅ **Secure access using JWT authentication.**
- ✅ **Role-based access control (Admin & User).**

---

## ⚙️ API Endpoints

### 📂 **Portfolio API**
| Method  | Endpoint | Description | Access |
|---------|---------|-------------|--------|
| `POST`  | `/api/portfolios/create` | Creates a new portfolio. | `USER, ADMIN` |
| `GET`   | `/api/portfolios/find/{title}` | Retrieves a portfolio by title. | `USER, ADMIN` |
| `GET`   | `/api/portfolios/all` | Fetches all portfolios. | `ADMIN` |
| `PUT`   | `/api/portfolios/update/{title}` | Updates an existing portfolio. | `USER, ADMIN` |
| `DELETE` | `/api/portfolios/delete/{title}` | Deletes a portfolio. | `ADMIN` |

### 🔑 **Authentication API**
| Method  | Endpoint | Description |
|---------|---------|-------------|
| `POST`  | `/api/auth/register` | Registers a new user. |
| `POST`  | `/api/auth/login` | Logs in and returns a JWT token. |

---

## 🔒 Security & Authentication (JWT)
- **JWT Authentication** is implemented to secure access to portfolio data.
- **Access Control:**
  - 👤 **USER** → Can manage their own portfolio.
  - 🔑 **ADMIN** → Has full access to manage all portfolios.

### 🛠️ **How JWT Works**
1. **User logs in** using `/api/auth/login`.
2. **API returns a JWT token** which must be included in all API requests.
3. **JWT is passed in headers** as:
   ```http
   Authorization: Bearer <your-token>
   
🛠️ Tech Stack
Java 17
Spring Boot (Spring Data JPA, Spring Security, Spring Web, JWT)
Gradle (Build Tool)
MySQL (Database)
Docker (Deployment)
Swagger (API Documentation)
JUnit & Mockito (Testing)

🚀 Setup & Running the Project
1️⃣ Clone the Repository
git clone https://github.com/mrrufat353/InternIntelligence_PortfolioApiDevelopment.git
cd InternIntelligence_PortfolioApiDevelopment
2️⃣ MySQL Configuration
Ensure your MySQL database is running and update application.yaml:
spring:
  profiles:
    active: ${ACTIVE_PROFILE:dev}
    include:
      - db
server:
  port: 9093

applicaton-db.yaml:
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:mysql://${DB_CONNECTION_IP:localhost}:${DB_CONNECTION_PORT:1313}/PortfolioApi?allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8
    username: ${DB_CONNECTION_USERNAME:root}
    password: ${DB_CONNECTION_PASSWORD:password}
  jpa:
    show-sql: true
    generate-ddl: false
    hibernate:
      ddl-auto: update

3️⃣ Running with Gradle
./gradlew clean build
./gradlew bootRun
4️⃣ Running with Docker
If you prefer running the application in Docker:
docker-compose up -d

🧪 Running Tests
Unit Tests
./gradlew test
Check API Documentation (Swagger)
Once the backend is running, open your browser and visit: http://localhost:9093/swagger-ui/index.html

📌 Contact & Support
If you have any questions, issues, or suggestions, feel free to open an issue on GitHub! 😊
GitHub Repository: InternIntelligence_PortfolioApiDevelopment
