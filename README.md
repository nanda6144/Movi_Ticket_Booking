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


**The User Can see the Ticket details After Consuming the topic:**

<img width="1883" height="711" alt="log5" src="https://github.com/user-attachments/assets/d2a23d3d-28bd-4598-bac6-1489222c1091" />


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

  <img width="1266" height="480" alt="log1" src="https://github.com/user-attachments/assets/7161af6a-cfcc-488a-888a-ad1c70ee3bfd" />


---

## 💳 Payment Service

**Responsibilities:**
- Consumes validated booking events
- Processes payment transaction
- Publishes payment status event

**Kafka Topics:**
- Consumes → `movie-found`
- Produces → `payment-topic`

  <img width="1071" height="526" alt="log2" src="https://github.com/user-attachments/assets/80bec3a4-0dc7-4c41-b2b7-409abe1af14c" />


---

## 📩 Notification Service

**Responsibilities:**
- Consumes payment success events
- Sends notification to user (SMS)
- Publishes notification delivery status

**Kafka Topics:**
- Consumes → `payment-topic`
- Produces → `notification-sent`

  <img width="1447" height="584" alt="log3" src="https://github.com/user-attachments/assets/db567746-c4e4-42d7-83cc-6280c37443ad" />


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

<img width="1830" height="504" alt="log4" src="https://github.com/user-attachments/assets/21670e76-d61d-4a9a-a474-dbdd169c9d6c" />


---

**PostMan Request:** 

<img width="1902" height="947" alt="Request_Response" src="https://github.com/user-attachments/assets/c0455756-1441-4521-abd5-ab92bb5c3995" />


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

-User Service
   ↓
-booking-movie
   ↓
-Booking Service
   ↓
-movie-found
   ↓
-Payment Service
   ↓
-payment-topic
   ↓
-Notification Service
   ↓
-notification-sent
   ↓
-Ticket Generation Service
   ↓
-ticket-details
   ↓
-User Service (consume ticket)

