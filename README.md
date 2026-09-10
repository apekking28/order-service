# Order & Checkout Service Workflow

This backend project is built using **Quarkus (Java 17)** to fulfill the requirements of a distributed system integrating **Apache Kafka** and **PostgreSQL** (Task 1), alongside marketplace checkout workflow automation powered by the **Kogito** BPMN engine (Task 2). The entire service ecosystem is containerized using **Docker Compose** to ensure seamless portability, reproducibility, and production-ready deployment.

---

## 🚀 Tech Stack

* **Programming Language:** Java 17
* **Framework:** Quarkus (2.16.8.Final)
* **Workflow Engine:** Kogito (BPMN Checkout Process)
* **Messaging System:** Apache Kafka & Zookeeper
* **Database:** PostgreSQL 15
* **Containerization:** Docker & Docker Compose

---

## 📂 Core Features & System Overview

1. **Task 1: Kafka & Database Integration**
* Asynchronously consumes event data from the `orders-in` Kafka topic.
* Processes business logic and manages data persistence.
* Stores transaction results in PostgreSQL and publishes outputs back to the `orders-out` Kafka topic.


2. **Task 2: BPMN Process Checkout**
* Implements the marketplace checkout workflow using standard BPMN definitions (`checkout.bpmn`).
* Automates service tasks including *Reserve Stock*, *Process Payment*, and *Create Delivery Order*.
* Integrates state persistence backed by a relational database.



---

## 📊 BPMN Checkout Diagram
<img width="692" height="434" alt="Screenshot 2026-09-10 223301" src="https://github.com/user-attachments/assets/bcfdad1d-0779-4cdb-89f7-1e5f99ccbe65" />

---

## 🔄 System Workflow

1. **Order Initiation (Event-Driven):**
Clients or external systems publish order events to the `orders-in` Kafka topic.
2. **Consumption & Validation:**
The Quarkus service consumes messages from Kafka, runs business computations, and records initial entries into PostgreSQL.
3. **BPMN Execution (Kogito):**
The checkout engine triggers automated business process tasks to handle stock reservation, payment processing, and delivery creation seamlessly.
4. **Result Publication:**
Upon workflow completion, final order statuses are updated in the database and dispatched downstream via the `orders-out` Kafka topic.

---

## ⚙️ Setup & Execution Guide

Ensure **Docker Desktop** is active and running correctly in your terminal or WSL environment.

1. **Clone the repository:**
```bash
git clone https://github.com/apekking28/order-service.git

```


2. **Navigate to the project directory:**
```bash
cd order-service

```


3. **Build and start the project using Docker:**
```bash
docker compose up -d --build

```


4. **Verify container status:**
```bash
docker compose ps

```



---

## 🔗 Endpoints & Documentation

Once the containers are active and running, you can access the application interfaces and documentation via the following links:

* **Swagger UI (Interactive API Docs):** [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)
* **List Orders (Database Endpoint):** [http://localhost:8080/orders](http://localhost:8080/orders)
* **Quarkus Dev UI:** [http://localhost:8080/q/dev](http://localhost:8080/q/dev)
