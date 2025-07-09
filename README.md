# REST API - User & Role Management (Spring Boot + JWT)

Backend application written in Java + Spring Boot, for managing users and roles with full JWT authentication.

## 🔧 Technologies

- Java 17+
- Spring Boot
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Lombok
- JUnit + Mockito
- Swagger (OpenAPI)
- Docker (for database setup)

---

## 📦 Project Architecture

- `controller` – REST API layer
- `service` – business logic layer
- `repository` – database operations
- `model` – JPA entities
- `dto` – data transfer objects
- `exception` – custom exceptions and handler
- `security` – JWT and security config
- `config` – additional settings (e.g., env, Swagger)

---

## ⚙️ Configuration

The project uses environment variables loaded from `.env` files.

- `.env` – template file (do not edit, commit to repo)
- `.env.local` – local configuration (create and set values)

Example `.env.local`:

```
JWT_SECRET=your_jwt_secret_key
DATABASE_URL=jdbc:mysql://localhost:3306/your_db
ADMIN_PASSWORD=supersecret
```

The `application.yaml` reads these variables.

---

## 🚀 Running Locally

```bash
git clone https://github.com/your-username/restapi-java.git
cd restapi-java
cp .env .env.local  # create local env file and set values
./mvnw spring-boot:run
```

---

### 🛡️ Default Admin Account

After starting the project, a default admin user is available:

- **Email:** `admin@example.com`
- **Password:** value set in `.env.local` under `ADMIN_PASSWORD`

---

## 🐳 Running with Docker

The project can be run with Docker using the provided `docker-compose.yml`. See `.env.example` for Docker variables.
To build the .jar package, run the following command (including test configuration, change SECRET_KEY to your key):
```bash
./mvnw clean package \
-Dspring.profiles.active=test \
-Djwt.secret=SECRET_KEY \
-Dspring.datasource.url=jdbc:mysql://localhost:3308/test_db \
-Dspring.datasource.username=test_user \
-Dspring.datasource.password=test_pass
```
or 
```bash
./mvnw clean package -DskipTests
```
---

## 🧪 Running Tests

```bash
./mvnw test -Dspring.profiles.active=test -Djwt.secret=256bitkey -Dspring.datasource.url=jdbc:mysql://localhost:3308/test_db -Dspring.datasource.username=test_user -Dspring.datasource.password=test_pass
```

Make sure the Docker database matches these credentials.

---

## 📄 API Documentation

Available with Swagger UI at:

```
http://localhost:5001/swagger-ui/index.html
```

---

## 📌 Author

Project created for educational purposes and portfolio by Tomasz Dyda.
