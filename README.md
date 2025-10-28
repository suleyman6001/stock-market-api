# Stock Market API (Spring Boot + PostgreSQL + Polygon.io)

A personal backend project that fetches and stores delayed U.S. stock price data using the [Polygon.io](https://polygon.io) API.  
Built with Java 21, Spring Boot 3.x, and PostgreSQL, this project demonstrates clean architecture, JPA persistence, and modular service design — serving as a base for a future microservices setup.

---

## Tech Stack

| Category | Technology                       |
|-----------|----------------------------------|
| Language | Java 21                          |
| Framework | Spring Boot 3.x                  |
| Persistence | Spring Data JPA, Hibernate       |
| Database | PostgreSQL                       |
| HTTP Client | WebClient (Spring WebFlux)       |
| Build Tool | Maven                            |
| Testing | JUnit 5                          |
| Logging | SLF4J / Logback                  |
| External API | Polygon.io                       |

---

## Features

- Fetch and store stock data from Polygon.io  
- Retrieve the most recent (15-minute delayed) stock price snapshot  
- Automatically persist stock and price data in PostgreSQL  
- Layered architecture (Entity → Repository → Service → Controller)  
- Hibernate-based ORM mapping with JPA annotations  
- Simple configuration via `.properties` files and environment variables  
- Ready for REST endpoints and microservices modularization  

---

## Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/<your-username>/stock-market-api.git
cd stock-market-api
```

### 2. Configure PostgreSQL
Create a new database:
```sql
CREATE DATABASE stock_api;
```

### 3. Configure application properties
In `src/main/resources/`, configure `application.properties` with your own API key and DB details

### 4. Run the application
```bash
mvn spring-boot:run
```

The app starts on [http://localhost:8081](http://localhost:8081).

---

## Current Progress

| Component | Status |
|------------|---------|
| Entities (`Stock`, `CurrentStockDetails`) | Complete |
| Database connection (PostgreSQL + Hibernate) | Verified |
| Repositories (Spring Data JPA) | Working |
| Service layer (`StockService`, `PriceService`) | In progress |
| Polygon.io API integration | Next |
| REST controllers | Planned |
| Microservices modularization | Future milestone |

---

## Project Structure

```
src/
 ├─ main/
 │   ├─ java/com/example/stockapi/
 │   │   ├─ entity/
 │   │   ├─ repository/
 │   │   ├─ service/
 │   │   ├─ controller/        # coming soon
 │   │   └─ StockMarketApiApplication.java
 │   └─ resources/
 │       ├─ application.properties
 │       └─ application-local.properties (gitignored)
 └─ test/
```

---

## Example Usage (coming soon)

```bash
GET /api/stocks/NVDA
GET /api/stocks/NVDA/price
```

---

## Author

**Suleyman Rahimov**  
Software Engineer | Java • Spring Boot • PostgreSQL  
Based in Austria