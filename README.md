# 🎟️ Event Ticketing & Fraud Control RESTful API

# 🎟️ Event Ticketing & Fraud Control API

A production-ready **Modular Monolithic RESTful API** built with **Java 17** and **Spring Boot 3**, engineered around **Domain-Driven Design (DDD)** and **Clean Architecture** principles. The system provides secure ticket issuance, transfer mechanics, real-time validation scanning, and race-condition prevention using **Redis Distributed Locks**.

---

* Clean Architecture
* Modular Monolith Architecture
* User registration and authentication
* JWT-based authentication and authorization
* Redis-backed Refresh Token Whitelisting
* Refresh Token Rotation
* Redis Distributed Locking
* Transaction Logging
* Centralized Exception Handling
* Secure logout and token invalidation
* Event creation and event discovery
* Ticket purchasing
* Ticket ownership transfer
* Ticket scanning and validation
* Concurrent ticket-purchase protection
* Ticket transaction logging
* Application event publishing
* Centralized exception handling
* PostgreSQL persistence with Spring Data JPA

---
## 📑 Table of Contents

- [Overview](#-overview)
- [Key Features](#-key-features)
- [Architecture](#-architecture)
- [Clean Architecture](#-clean-architecture)
- [Modular Monolith](#-modular-monolith)
- [Module Structure](#-module-structure)
- [Identity and Security](#-identity-and-security)
- [JWT Authentication](#-jwt-authentication)
- [Redis Refresh Token Whitelisting](#-redis-refresh-token-whitelisting)
- [Refresh Token Rotation](#-refresh-token-rotation)
- [Logout and Token Invalidation](#-logout-and-token-invalidation)
- [Current User Resolution](#-current-user-resolution)
- [Ticketing Engine](#-ticketing-engine)
- [Event Management](#-event-management)
- [Ticket Management](#-ticket-management)
- [Ticket Lifecycle](#-ticket-lifecycle)
- [Redis Distributed Locking](#-redis-distributed-locking)
- [Concurrency Control](#-concurrency-control)
- [Application Events](#-application-events)
- [Transaction Logging](#-transaction-logging)
- [Repository Abstraction](#-repository-abstraction)
- [DTO and Mapping Strategy](#-dto-and-mapping-strategy)
- [Exception Handling](#-exception-handling)
- [API Endpoints](#-api-endpoints)
- [Technology Stack](#-technology-stack)
- [Project Structure](#-project-structure)
- [Requirements](#-requirements)
- [Configuration](#-configuration)
- [Running the Application](#-running-the-application)
- [Testing](#-testing)
- [API Documentation](#-api-documentation)
- [Security Design](#-security-design)
- [Fraud Control](#-fraud-control)
- [Engineering Principles](#-engineering-principles)
- [Scalability Considerations](#-scalability-considerations)
- [Future Improvements](#-future-improvements)
- [Project Status](#-project-status)
- [Author](#-author)

---
## 📌 Project Overview

Traditional monolithic applications can become difficult to maintain when business capabilities are tightly coupled together.

This project takes a different approach by implementing a **Modular Monolith** architecture.

Instead of immediately introducing microservices, the system separates major business capabilities into well-defined modules while still being deployed as a single application.

The application combines:

- Clean Architecture
- Modular Monolith Architecture
- Domain-oriented business rules
- Use Case driven application design
- JWT-based authentication
- Redis-backed refresh-token management
- Redis distributed locking
- Event-driven transaction logging
- PostgreSQL persistence

The main architectural objective is to keep **business logic independent from infrastructure and framework-specific implementations**.

---

# ✨ Key Features

## 🔐 Authentication and Security

- User registration
- Username/password authentication
- Password encoding
- JWT access tokens
- JWT refresh tokens
- JWT validation
- Refresh-token validation
- Redis-backed refresh-token whitelisting
- Refresh-token rotation
- Refresh-token expiration
- Server-side token invalidation
- Logout
- Spring Security integration
- Role-based authentication information
- Current authenticated-user resolution

## 🎫 Event and Ticket Management

- Event creation
- Event retrieval
- Ticket purchasing
- Ticket availability management
- Ticket scanning
- Ticket ownership transfer
- Current-user ticket retrieval
- Ticket lifecycle management

## 🔒 Concurrency and Fraud Control

- Redis distributed locks
- Event-level purchase locking
- Ticket-level scan locking
- Protection against concurrent ticket operations
- Ticket state validation
- Prevention of repeated ticket usage
- Ownership validation
- Transaction logging
- Application event-based transaction processing

## 🏛️ Architecture Overview

The system is designed as a **Modular Monolith** to enforce high cohesion and loose coupling across domain boundaries. The core business logic is split into isolated modules:

1. **Identity & Security Module:** Handles user registration, JWT authentication, token rotation, and RBAC (`ATTENDEE`, `ORGANIZER`).
2. **Ticketing Engine Module:** Manages event publishing, ticket purchasing, ticket transferring, and counter scanning.
3. **Shared Module:** Contains cross-cutting concerns such as custom Exception Handling, Web Resolvers, and Redis Configuration.


- Clean Architecture
- Modular Monolith
- Domain-oriented design
- Use Case driven application layer
- Dependency Inversion
- Repository abstractions
- Infrastructure isolation
- DTO separation
- Mapper abstraction
- Centralized exception handling

---
### High-Level Architecture

```text
                         ┌─────────────────────────┐
                         │        REST API         │
                         │       Controllers       │
                         └────────────┬────────────┘
                                      │
                                      ▼
                         ┌─────────────────────────┐
                         │      Application        │
                         │       Use Cases         │
                         └────────────┬────────────┘
                                      │
                                      ▼
                         ┌─────────────────────────┐
                         │         Domain          │
                         │ Entities / Rules / Ports│
                         └────────────┬────────────┘
                                      │
                         ┌────────────┴────────────┐
                         ▼                         ▼
              ┌─────────────────────┐   ┌─────────────────────┐
              │   Infrastructure    │   │   Shared Services   │
              │                     │   │                     │
              │ JPA / PostgreSQL    │   │ Redis                │
              │ JWT                 │   │ Exception Handling   │
              │ Application Events  │   │ Current User Resolver│
              │ Repository Impl.    │   │                     │
              └─────────────────────┘   └─────────────────────┘
```

---

# 🧩 Modular Monolith

The project is intentionally structured as a **Modular Monolith** instead of immediately splitting the system into microservices.

```text
src/main/java/
└── lk/modular/monolithic/event/ticketing/fraud/control/restful/api
    │
    ├── modules/
    │   │
    │   ├── identity_security/
    │   │   ├── domain/
    │   │   ├── usecase/
    │   │   ├── infrastructure/
    │   │   └── web/
    │   │
    │   └── ticketing_engine/
    │       ├── domain/
    │       ├── usecase/
    │       ├── infrastructure/
    │       └── web/
    │
    └── shared/
        ├── error_handling/
        ├── redis/
        ├── web_resolver/
        └── shared_domain/
```

The source demonstrates this separation through dedicated `identity_security` infrastructure for JWT, user persistence, Redis token storage, and Spring Security, while the `ticketing_engine` contains event and ticket use cases and domain logic.

---

## 🔐 Identity & Security Module

The `identity_security` module is responsible for authentication and user security.

### Responsibilities

* User registration
* Password encoding
* User authentication
* JWT generation
* JWT validation
* Refresh-token management
* Redis token storage
* Refresh-token whitelisting
* Refresh-token rotation
* Spring Security integration
* Logout

The authentication API exposes the following operations:

| Method | Endpoint                     | Description                         |
| ------ | ---------------------------- | ----------------------------------- |
| `POST` | `/api/v1/auth/register`      | Register a new user                 |
| `POST` | `/api/v1/auth/login`         | Authenticate a user                 |
| `POST` | `/api/v1/auth/refresh-token` | Generate new authentication tokens  |
| `POST` | `/api/v1/auth/logout`        | Invalidate the current user session |

These endpoints are implemented by the `AuthController`.

---

## 🔑 JWT Authentication

The application uses JWT-based authentication with two token types:

 - Access Token
 - Refresh Token

The JWT provider is responsible for:
```
Generate Access Token
Generate Refresh Token
Validate Token
Extract Claims
Extract Email
Get Refresh Token Expiration
```
The authentication response contains information such as:

```
Access Token
Refresh Token
Token Type
User ID
Email
Role
```

---

## ♻️ Redis Refresh Token Whitelisting

One of the key security features of this system is server-side refresh-token whitelisting using Redis.
The system does not rely exclusively on JWT signature validation for refresh-token authentication.
Instead, refresh-token validation uses two levels of verification:

```
                   Refresh Token
                         |
                         v
               +---------------------+
               | Validate JWT        |
               +----------+----------+
                          |
                          v
                  Extract User
                          |
                          v
                 Find User in DB
                          |
                          v
               Retrieve Token Redis
                          |
                          v
                  Compare Tokens
                          |
             +------------+------------+
             |                         |
          MATCH                     MISMATCH
             |                         |
             v                         v
        Continue                    Reject

```
### Validation Process

When a refresh request is received:

1. Validate the JWT.
2. Extract the user's identity.
3. Verify that the user exists.
4. Retrieve the active refresh token from Redis.
5. Compare the Redis token with the supplied token.
6. Reject the request if the token does not exist.
7. Reject the request if the token does not match.
8. Generate new authentication tokens when validation succeeds.

This gives the application server-side control over refresh-token sessions.

---
### 🔄 Refresh Token Rotation

The system implements Refresh Token Rotation.
After a successful refresh operation, a new refresh token is generated and stored in Redis.

```
Old Refresh Token
        |
        v
   Validate JWT
        |
        v
 Redis Whitelist Check
        |
        v
 Generate New Tokens
        |
        +--------------------+
        |                    |
        v                    v
 New Access Token     New Refresh Token
                            |
                            v
                       Update Redis

```

The old refresh token is replaced by the newly generated refresh token.
This reduces the ability to reuse previously issued refresh tokens.

---
### 🚪 Logout and Token Invalidation

Logout removes the active refresh-token state from Redis.

```
Logout
  |
  v
Identify User
  |
  v
Delete Refresh Token
  |
  v
Redis Whitelist Removed
  |
  v
Refresh Session Invalidated
```
This provides server-side refresh-session invalidation.

---
## 👤 Current User Resolution

The project introduces a custom `@CurrentUserId` annotation.

Instead of repeatedly extracting the authenticated user from `SecurityContextHolder` inside controllers, controllers can receive the current user's ID directly.

Example conceptual usage:

```text
@CurrentUserId Long userId
```

```
HTTP Request
     |
     v
Spring Security
     |
     v
Authenticated User
     |
     v
SpringSecurityUserProvider
     |
     v
CurrentUserIdArgumentResolver
     |
     v
@CurrentUserId
```

The custom argument resolver obtains the authenticated user's ID through `SpringSecurityUserProvider`.
This keeps authentication-related infrastructure away from business logic.

---

# 🎫 Ticketing Engine

The `ticketing_engine` module contains the core event and ticket business logic.

### Main responsibilities

* Event creation
* Event retrieval
* Ticket purchasing
* Ticket availability management
* Ticket scanning
* Ticket ownership transfer
* User ticket retrieval
* Ticket state management
* Ticket transaction logging

---

## 🎪 Event Management

Events contain information such as:

* Event title
* Description
* Location
* Event date
* Total tickets
* Available tickets
* Ticket price
* Organizer
* Creation timestamp

The event API provides:

| Method | Endpoint                   | Description               |
| ------ | -------------------------- | ------------------------- |
| `POST` | `/api/v1/events`           | Create an event           |
| `GET`  | `/api/v1/events`           | Retrieve all events       |
| `GET`  | `/api/v1/events/{eventId}` | Retrieve a specific event |

Event creation also associates the authenticated user as the organizer through the custom current-user mechanism.

---

## 🎟️ Ticket Management

The ticket API provides the following operations:

| Method | Endpoint                            | Description                     |
| ------ | ----------------------------------- | ------------------------------- |
| `POST` | `/api/v1/tickets/buy/{eventId}`     | Purchase a ticket               |
| `POST` | `/api/v1/tickets/scan/{ticketCode}` | Scan and validate a ticket      |
| `POST` | `/api/v1/tickets/transfer-ticket`   | Transfer ticket ownership       |
| `GET`  | `/api/v1/tickets/my-tickets`        | Retrieve current user's tickets |

---

## 🧠 Domain-Driven Business Rules

Business rules are placed inside domain models rather than being implemented entirely inside controllers.

For example, the `Ticket` domain object controls ticket usage and ownership transfer.

### Ticket usage rules

A ticket cannot be used when:

* It has already been used
* It has been cancelled
* It has been refunded

When a valid ticket is scanned, its state changes to `USED` and the scan timestamp is recorded.

### Ticket transfer rules

Only tickets in the `PURCHASED` state can be transferred.

After a successful transfer:

```text
Current Owner
     │
     ▼
New Owner
     │
     ▼
Ticket Status = TRANSFERRED
```

---

## 📊 Ticket States

The domain defines the following ticket states:

```text
PURCHASED
USED
CANCELLED
REFUNDED
TRANSFERRED
PURCHASED_SUCCESSFULLY
```

---

## 🔒 Concurrency Control with Redis

One of the most important parts of this system is the handling of concurrent ticket operations.

A typical ticketing system can face a race condition such as:

```text
Available Tickets = 1

User A ────────┐
               ├──► Check availability ──► 1
User B ────────┘
               └──► Check availability ──► 1

Both requests attempt to purchase the final ticket.
```

Without concurrency protection, both requests could potentially attempt to reserve the same ticket.

This project introduces a Redis-based distributed lock.

---

## 🛒 Ticket Purchase Lock

During ticket purchasing, a lock is created using an event-specific lock key.

```text
Ticket Purchase Request
        │
        ▼
Generate Lock Key
        │
        ▼
Acquire Redis Lock
        │
   ┌────┴────┐
   │         │
 Failed    Success
   │         │
   ▼         ▼
Conflict   Check Event
 Response      │
               ▼
        Reserve Ticket
               │
               ▼
          Save Ticket
               │
               ▼
        Publish Event
               │
               ▼
         Release Lock
```

 * The purchase use case creates a lock using an event-specific prefix and UUID lock value, validates ticket availability, reserves the ticket, persists the ticket, publishes a purchase event, and releases the lock in a `finally` block.

---

## 🎫 Ticket Scan Protection

The same concurrency principle is applied to ticket scanning.
A ticket-specific Redis lock prevents multiple scan requests from processing the same ticket concurrently.

```text
Scan Request
     │
     ▼
Create Ticket Lock
     │
     ▼
Acquire Redis Lock
     │
     ├── Failed ──► Reject Request
     │
     ▼
Find Ticket
     │
     ▼
Validate Ticket State
     │
     ▼
Mark As USED
     │
     ▼
Persist Ticket
     │
     ▼
Release Lock
```

The scan use case creates a lock using the ticket code and releases it after processing.

---

## 🧾 Transaction Logging

The system maintains ticket transaction records for traceability.

```
Ticket Purchase
      |
      v
TicketPurchasedEvent
      |
      v
Event Listener
      |
      v
Create Transaction Log
      |
      v
Persist Transaction
```

Transaction records contain information including:

* Transaction ID
* Ticket ID
* User ID
* Event ID
* Ticket price
* Timestamp
* Ticket status

The persistence model stores these records in the `table_logs.ticket_logs` table.

---

## 📡 Application Event Publishing

Ticket purchases publish an application event:

```text
TicketPurchasedEvent
```

The event contains:

* Ticket ID
* User ID
* Event ID
* Ticket price
* Timestamp

The system uses Spring's `ApplicationEventPublisher` through an abstraction:

```text
EventPublisher
       │
       ▼
EventPublisherImpl
       │
       ▼
ApplicationEventPublisher
```

The published event is then handled by the ticket-log listener, which creates a transaction log with the `PURCHASED_SUCCESSFULLY` status.

This creates a clean separation between the ticket purchase operation and transaction-log processing.

---

## 🛡️ Fraud-Control-Oriented Design

The system does not rely on a single "fraud detection" algorithm.

Instead, fraud-control is approached through **transaction integrity and abuse prevention mechanisms**, including:

### 1. Concurrent Purchase Protection

Redis distributed locking prevents multiple requests from simultaneously modifying the same event's ticket availability.

### 2. Concurrent Ticket Scan Protection

Ticket-code-specific locking prevents simultaneous scan operations from processing the same ticket concurrently.

### 3. Ticket State Validation

Domain rules prevent:

* Reusing an already-used ticket
* Using cancelled tickets
* Using refunded tickets
* Transferring an ineligible ticket

### 4. Transaction Traceability

Ticket purchase events are recorded as transaction logs containing user, ticket, event, price, status, and timestamp information.

### 5. Server-Side Refresh Token State

Refresh tokens are maintained in Redis, allowing authentication state to be invalidated server-side.

---


## ⚠️ Centralized Exception Handling

The application uses a centralized `GlobalExceptionHandler` through `@RestControllerAdvice`.

Supported application-level errors include:

| HTTP Status | Exception Type              |
| ----------- | --------------------------- |
| `400`       | `BadRequestException`       |
| `400`       | Validation errors           |
| `400`       | `InvalidTicketException`    |
| `401`       | `UnauthorizedException`     |
| `403`       | `ForbiddenException`        |
| `404`       | `ResourceNotFoundException` |
| `409`       | `ConflictException`         |
| `500`       | Unexpected internal errors  |

---

## 📦 Standard API Response

The project provides a generic `ApiResponse<T>` wrapper.

### Successful response

```json
{
  "success": true,
  "data": {}
}
```

### Error response

```json
{
  "success": false,
  "errorDetails": {
    "status": 400,
    "message": "Invalid request",
    "description": "...",
    "timestamp": "2026-01-01T10:00:00"
  }
}
```

The response abstraction keeps successful and error responses consistent across the API.


---
### 📁 Project Structure

```

```
---

## 🛠️ Technology Stack

| Technology               | Purpose                             |
| ------------------------ | ----------------------------------- |
| Java 17                  | Programming language                |
| Spring Boot 3.5.5        | Application framework               |
| Spring Web               | REST API                            |
| Spring WebFlux           | Reactive HTTP capabilities          |
| Spring Security          | Authentication & authorization      |
| Spring Data JPA          | Persistence abstraction             |
| PostgreSQL               | Relational database                 |
| Redis                    | Token storage & distributed locking |
| JJWT 0.12.6              | JWT generation & validation         |
| Spring Modulith 1.2.4    | Modular application architecture    |
| MapStruct 1.6.3          | DTO/entity mapping                  |
| Lombok                   | Boilerplate reduction               |
| Jackson JSR-310          | Java time serialization             |
| Spring REST Docs         | API documentation/testing support   |
| JUnit / Spring Boot Test | Testing                             |
| Spring Security Test     | Security testing                    |
| Asciidoctor              | Documentation generation            |

---

# 🔄 Core Business Flows

## Authentication Flow

```text
Register
   │
   ▼
User Repository
   │
   ▼
PostgreSQL


Login
   │
   ▼
Authenticate Credentials
   │
   ▼
Generate Access + Refresh Tokens
   │
   ├──────────────► Access Token
   │
   └──────────────► Redis Refresh Token
```

---

## Ticket Purchase Flow

```text
POST /api/v1/tickets/buy/{eventId}
                 │
                 ▼
        Get Current User
                 │
                 ▼
        Acquire Redis Lock
                 │
                 ▼
          Find Event
                 │
                 ▼
       Check Availability
                 │
                 ▼
          Reserve Ticket
                 │
                 ▼
        Save Event State
                 │
                 ▼
        Generate Ticket Code
                 │
                 ▼
          Save Ticket
                 │
                 ▼
       Publish TicketPurchasedEvent
                 │
                 ▼
      Create Transaction Log
                 │
                 ▼
          Release Lock
```

The implementation explicitly acquires a Redis lock before checking availability and releases that lock after processing.

---

## Ticket Scan Flow

```text
Ticket Code
     │
     ▼
Acquire Redis Lock
     │
     ▼
Find Ticket
     │
     ▼
Validate Ticket State
     │
     ├── Already Used ──► Reject
     │
     ├── Cancelled ─────► Reject
     │
     └── Valid
          │
          ▼
      Mark USED
          │
          ▼
       Persist
          │
          ▼
     Release Lock
```

---

## Ticket Transfer Flow

```text
Current User
     │
     ▼
Ticket ID + New Owner ID
     │
     ▼
Validate Ownership
     │
     ▼
Validate Ticket State
     │
     ▼
Transfer Ownership
     │
     ▼
Ticket Status = TRANSFERRED
     │
     ▼
Persist Ticket
```

The domain layer explicitly restricts transfers to tickets whose state is `PURCHASED`.

---

## 🧩 Design Principles

The project emphasizes several backend engineering principles.

### Separation of Concerns

 - Controllers handle HTTP concerns.
 - Use cases handle application operations.
 - Domain models contain business rules.
 - Infrastructure handles external technologies.

---

### Dependency Inversion

The domain defines repository interfaces while infrastructure provides implementations.

```text
Domain Interface
      ▲
      │
Infrastructure Implementation
```

This reduces direct coupling between business logic and frameworks.

---

### Domain-Centric Business Rules

Business rules such as ticket usage and transfer validation are implemented in the domain model rather than being scattered across controllers.

---

### Explicit Use Cases

Instead of placing business logic directly inside controllers, operations are represented through dedicated use cases:

```text
CreateEventUseCase
GetAllEventsUseCase
EventByIdUseCase

BuyTicketUseCase
ScanTicketUseCase
TransferTicketUseCase
GetMyTicketsUseCase
```

This makes business operations explicit and easier to test and evolve.

---

### Infrastructure Isolation

External technologies such as:

* PostgreSQL
* Redis
* JWT
* Spring Security
* Spring Data JPA

are kept inside infrastructure-oriented implementations rather than becoming dependencies of the core domain.

---

## 📁 Important Package Responsibilities

| Package                     | Responsibility                                         |
| --------------------------- | ------------------------------------------------------ |
| `modules.identity_security` | Authentication and user security                       |
| `modules.ticketing_engine`  | Events and ticket business logic                       |
| `domain`                    | Business models, rules and repository contracts        |
| `usecase`                   | Application-specific operations                        |
| `infrastructure`            | Persistence, security, Redis and event implementations |
| `web`                       | Controllers, DTOs and web mappers                      |
| `shared.redis`              | Redis configuration                                    |
| `shared.error_handling`     | Centralized exception handling                         |
| `shared.web_resolver`       | Current authenticated-user resolution                  |
| `shared.shared_domain`      | Cross-module contracts                                 |

---

## 🔍 Example API Usage

## Register

```http
POST /api/v1/auth/register
Content-Type: application/json
```

```json
{
  "email": "user@example.com",
  "password": "password"
}
```

---

## Login

```http
POST /api/v1/auth/login
Content-Type: application/json
```

```json
{
  "email": "user@example.com",
  "password": "password"
}
```

Response:

```json
{
  "accessToken": "...",
  "refreshToken": "...",
  "tokenType": "Bearer",
  "userId": 1,
  "email": "user@example.com",
  "role": "USER"
}
```

The authentication response structure is defined by `AuthResponseDTO`.

---

## Create Event

```http
POST /api/v1/events
Authorization: Bearer <access-token>
Content-Type: application/json
```

```json
{
  "eventTitle": "Spring Boot Conference",
  "eventDescription": "Backend engineering conference",
  "eventLocation": "Colombo",
  "eventDate": "2027-01-20T10:00:00",
  "eventTotalTickets": 500,
  "eventTicketPrice": 2500
}
```

The event request applies validation such as required title/location, future event date, positive ticket count, and required ticket price.

---

## Purchase Ticket

```http
POST /api/v1/tickets/buy/1
Authorization: Bearer <access-token>
```

---

## Scan Ticket

```http
POST /api/v1/tickets/scan/<ticket-code>
Authorization: Bearer <access-token>
```

---

## Transfer Ticket

```http
POST /api/v1/tickets/transfer-ticket
Authorization: Bearer <access-token>
Content-Type: application/json
```

```json
{
  "ticketId": 10,
  "newOwnerId": 25
}
```

---

## Get My Tickets

```http
GET /api/v1/tickets/my-tickets
Authorization: Bearer <access-token>
```

---

## 📈 Scalability Considerations

Although this project follows a Modular Monolith architecture, several design decisions make future evolution easier.

### Module Isolation

Business capabilities are separated into modules rather than being mixed throughout the application.

### Redis-Based Coordination

Redis provides distributed coordination for high-contention ticket operations.

### Event-Based Processing

Ticket purchase events are published through an abstraction, allowing additional listeners or integrations to be introduced without tightly coupling them to the ticket purchase use case.

### Repository Abstraction

Persistence implementations can evolve independently of the domain layer.

### Explicit Use Cases

Business operations remain isolated and easier to extract or reorganize if the architecture evolves.

---

## 🧪 Engineering Focus

This project demonstrates practical backend engineering concepts including:

* Modular Monolith Architecture
* Domain-Oriented Design
* Clean separation of domain and infrastructure
* Use Case Driven Application Design
* Repository Pattern
* DTO Pattern
* Mapper Pattern
* JWT Authentication
* Refresh Token Management
* Redis
* Distributed Locking
* Concurrency Control
* Transaction Logging
* Application Events
* Spring Security
* Spring Data JPA
* PostgreSQL
* Centralized Exception Handling
* Bean Validation
* REST API Design
* REST Docs
* Automated Testing

---


## 📜 Project Status

**Status:** 🚧 Development / Learning Project

This project is primarily focused on demonstrating advanced backend architecture and engineering practices using the Spring ecosystem.

---

## 👨‍💻 Author

**Yahan Ravinga**

Programming Enthusiast | Java & Spring Backend Developer

Interested in:

* Backend Engineering
* Java & Spring Boot
* Software Architecture
* Clean Code
* Distributed Systems
* Scalable Application Design
* Security
* Continuous Learning

---

## ⭐ Key Takeaway

The primary architectural goal of this project is not simply to build an event-ticketing CRUD API.

It demonstrates how a backend system can combine:

```text
                    Modular Monolith
                          │
            ┌─────────────┴─────────────┐
            │                           │
     Identity & Security          Ticketing Engine
            │                           │
            ▼                           ▼
          JWT                    Domain Business Rules
            │                           │
            ▼                           ▼
         Redis ◄──────────────► Distributed Locks
            │                           │
            └─────────────┬─────────────┘
                          │
                          ▼
                 Application Events
                          │
                          ▼
                 Transaction Logging
                          │
                          ▼
                     PostgreSQL
```

The result is a **modular, security-aware, concurrency-conscious event ticketing backend** designed with clear separation between business logic and infrastructure.
