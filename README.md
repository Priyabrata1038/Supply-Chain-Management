# Supply Chain Management System

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green.svg)](https://spring.io/projects/spring-boot)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Issues](https://img.shields.io/github/issues/yourusername/supply-chain-management.svg)](https://github.com/yourusername/supply-chain-management/issues)

A robust **Spring Boot-based RESTful API** for managing supply chain operations. This application handles entities like Customers, Orders, Products, and Suppliers with full CRUD operations, pagination, sorting, and custom queries (e.g., by phone, tracking number, status). It uses Spring Data JPA for database interactions and provides standardized JSON responses.

## Features
- **Entities & Relationships**:
  - **Customer**: One-to-Many with Orders (e.g., fetch customer by order ID).
  - **Orders**: Many-to-One with Customer; Many-to-Many with Products (e.g., filter by status or total amount > X).
  - **Product**: Many-to-One with Supplier (e.g., fetch by stock quantity).
  - **Supplier**: One-to-Many with Products (e.g., fetch by product ID).
- **Core Functionalities**:
  - CRUD operations for all entities.
  - Pagination and sorting (e.g., `/api/order/page/0/10/sort/id`).
  - Custom queries: By phone, tracking number, status, stock quantity, etc.
  - Exception handling for invalid IDs and empty records.
  - Auto-generated timestamps for orders.
- **Tech Stack**:
  - Backend: Spring Boot 3.x, Spring Data JPA, Hibernate.
  - Database: MySQL (configurable).
  - API: RESTful with JSON responses (using `ResponseStructure` DTO).
  - Other: Jackson for serialization, Lombok (if added for getters/setters).

## Demo
- Run the app and test via Postman or Swagger (add Springdoc dependency for auto-docs).
- Sample Endpoint: `POST /api/order` to create an order with products.

## Prerequisites
- Java 17+.
- Maven 3.6+.
- MySQL 8.0+ (or H2 for testing).
- IDE: IntelliJ IDEA or Eclipse.

## Installation & Setup
1. **Clone the Repository**:
