Stock Market API

A Spring Boot REST API that fetches stock market data from Polygon.io, persists it in PostgreSQL, and exposes endpoints for querying stored stock information.
Built with Java 21, Spring Boot 3.x, PostgreSQL and dockerized with Docker Compose, this project demonstrates clean architecture, JPA persistence, and modular service design — serving as a base for a future microservices setup.

The project supports:
- Local development via IntelliJ
- Containerized execution via Docker & Docker Compose


Tech Stack
- Java 21
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven (via Maven Wrapper)
- Docker & Docker Compose
- Polygon.io API


Prerequisites

For local IntelliJ run:
- Java 21
- PostgreSQL (running locally)
- IntelliJ IDEA

For Docker run:
- Docker Desktop
- No local PostgreSQL required


Configuration Overview (Important)

This project uses externalized configuration.

- application.properties
  Contains placeholders only (no secrets)

- application-local.properties (not committed)
  Used only for local IntelliJ runs

- .env (not committed)
  Used only for Docker Compose runs

Secrets are never committed.


Option A — Run Locally in IntelliJ (Recommended for Development)

1) Create local configuration file

Create:
src/main/resources/application-local.properties

Example:

POLYGON_API_KEY=YOUR_API_KEY_HERE
DB_URL=jdbc:postgresql://localhost:5432/stock_api
DB_USER=postgres
DB_PASS=postgres


2) Enable the local Spring profile in IntelliJ

Run / Debug Configurations → Environment variables:
SPRING_PROFILES_ACTIVE=local


3) Create local database

CREATE DATABASE stock_api;


4) Run the application

Run StockMarketApiApplication from IntelliJ.

API available at:
http://localhost:8081


Option B — Run with Docker & Docker Compose

1) Create .env file (NOT committed)

In project root, create .env:

POLYGON_API_KEY=YOUR_API_KEY_HERE
DB_URL=jdbc:postgresql://db:5432/stock_api
DB_USER=postgres
DB_PASS=postgres


2) Build the application JAR (Windows)

Use Maven Wrapper:

.\mvnw.cmd clean install "-Dmaven.test.skip=true"


3) Start containers

docker compose up --build

API available at:
http://localhost:8081


4) Stop containers

docker compose down

Reset DB data:
docker compose down -v


Common Notes

- In Docker Compose, PostgreSQL hostname is 'db'
- .env and application-local.properties must never be committed
- If DB credentials change, you may need docker compose down -v


Project Structure (Relevant Files)
Dockerfile
docker-compose.yml
.env (not committed)
src/main/resources/application.properties
src/main/resources/application-local.properties (not committed)


License
Educational and portfolio purposes.
