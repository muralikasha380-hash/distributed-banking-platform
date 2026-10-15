# Distributed Banking Platform

## Overview

Distributed Banking Platform is a Banking Management System developed using Spring Boot, Spring Data JPA, Hibernate, and Oracle Database.

The application allows users to create accounts, deposit money, withdraw money, transfer funds between accounts, and view transaction history through REST APIs.

## Features

- Create Bank Account
- View Account Details
- View All Accounts
- Deposit Money
- Withdraw Money
- Transfer Funds
- Transaction History
- Exception Handling
- Oracle Database Integration

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Oracle Database
- Maven
- Postman

## Project Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Oracle Database
```

## API Endpoints

### Account APIs

#### Create Account

```http
POST /api/accounts
```

#### Get All Accounts

```http
GET /api/accounts
```

#### Get Account By ID

```http
GET /api/accounts/{id}
```

### Transaction APIs

#### Deposit Money

```http
POST /api/accounts/{id}/deposit
```

#### Withdraw Money

```http
POST /api/accounts/{id}/withdraw
```

#### Transfer Funds

```http
POST /api/accounts/transfer
```

#### Transaction History

```http
GET /api/accounts/{id}/transactions
```

## Key Implementations

- Layered Architecture
- DTO Pattern
- Repository Pattern
- Transaction Management
- Input Validation
- Custom Exception Handling
- Spring Data JPA Integration
- Oracle Database Connectivity

## Author

Murali Krishna