🚀 Project Overview

This system simulates a real-world movie ticket booking workflow where each service communicates via Kafka events instead of direct synchronous API calls. This ensures:

✅Loose coupling

✅High scalability

✅Fault tolerance

✅Parallel processing

✅Event replay capability




# 🎬 Movie Ticket Booking System — Kafka Microservices Project
Event-driven Movie Ticket Booking system built using Apache Kafka and Microservices architecture for asynchronous user, movie, payment, Notification and ticket-Generation processing.


## 🚀 Features

- ✅ Microservices-based architecture
- ✅ Apache Kafka event streaming
- ✅ Asynchronous order processing
- ✅ Payment processing workflow
- ✅ Ticket Generation service
- ✅ Notification service
- ✅ Fault-tolerant message handling
- ✅ Consumer group parallelism
- ✅ Partition-based scaling
- ✅ Offset tracking & replay support

---
# Each service communicates through Kafka topics instead of direct REST calls for better scalability and resilience.



# 🧩 Microservices

## 🎟 User Service (Entry + Final Consumer)

**Responsibilities:**
- Accept movie ticket booking requests
- Publish booking request event
- Consume final ticket details
- Return ticket to user

**Kafka Topics:**
- Produces → `booking-movie`
- Consumes → `ticket-details`

---

## 🎟 Booking Service

**Responsibilities:**
- Consumes booking requests
- Performs validations:
  - Movie exists
  - Show Availability tickets
- Creates booking record
- Publishes validated booking event

**Kafka Topics:**
- Consumes → `booking-movie`
- Produces → `movie-found`

---

## 💳 Payment Service

**Responsibilities:**
- Consumes validated booking events
- Processes payment transaction
- Publishes payment status event

**Kafka Topics:**
- Consumes → `movie-found`
- Produces → `payment-topic`

---

## 📩 Notification Service

**Responsibilities:**
- Consumes payment success events
- Sends notification to user (SMS)
- Publishes notification delivery status

**Kafka Topics:**
- Consumes → `payment-topic`
- Produces → `notification-sent`

---

## 🎫 Ticket Generation Service

**Responsibilities:**
- Consumes notification success events
- Generates movie ticket
- Creates ticket metadata:
  - Movie Name
  - Ticket ID
  - Theatre Location
  - Show Date & Time
  - Theatre Screen Number
  - Theatre Name
  - Ticket Booked Under Name  
- Publishes final ticket details

**Kafka Topics:**
- Consumes → `notification-sent`
- Produces → `ticket-details`

---

---

# 📨 Kafka Topics Summary

| Topic            | Produced By               | Consumed By                |
|------------------|---------------------------|----------------------------|
| booking-movie    | User Service              | Booking Service            |
| movie-found      | Booking Service           | Payment Service            |
| payment-topic    | Payment Service           | Notification Service       |
| notification-sent| Notification Service      | Ticket Generation Service  |
| ticket-details   | Ticket Generation Service | User Service               |





# ⚙️ Tech Stack

- Java
- Spring Boot
- Spring Kafka
- Apache Kafka
- REST APIs
- Maven
- Docker (optional)
- Zookeeper / KRaft mode
- Multithreading




## 🎫 End-to-End Event Flow

User Service
   ↓
booking-movie
   ↓
Booking Service
   ↓
movie-found
   ↓
Payment Service
   ↓
payment-topic
   ↓
Notification Service
   ↓
notification-sent
   ↓
Ticket Generation Service
   ↓
ticket-details
   ↓
User Service (consume ticket)

