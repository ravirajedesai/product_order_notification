# 🛒 E-Commerce Microservices Application

This project is a **microservices-based E-Commerce system** built using **Spring Boot**, **Kafka**, **Feign Client**, and **MySQL/H2**.  
It demonstrates **service-to-service communication**, **event-driven architecture**, and **fault tolerance** using modern Spring Cloud components.

---

## 🧩 Microservices Overview

### 1️⃣ Product Service
- Manages product catalog
- Handles product CRUD operations
- Exposes REST APIs for product details
- Communicates with Order Service via **Feign Client**

### 2️⃣ Order Service
- Manages customer orders
- Fetches product details using **Feign Client**
- Publishes order events to **Kafka**
- Acts as the central orchestration service

### 3️⃣ Notification Service
- Consumes order events from **Kafka**
- Sends notifications (email/log-based simulation)
- Fully decoupled from Order Service

---

## 🛠 Tech Stack

- **Java 21**
- **Spring Boot 3.2.5**
- **Spring Data JPA**
- **Spring Cloud OpenFeign**
- **Apache Kafka**
- **Resilience4j (Circuit Breaker)**
- **MySQL / H2 (for testing)**
- **JUnit 5 & Mockito**
- **Maven**

---

## 🏗 Architecture Diagram (High-Level)

