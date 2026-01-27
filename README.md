# Microservices Architecture with Eureka Server and JWT Authentication

## 📌 Project Overview
This project follows a **Microservices Architecture** using **Spring Boot** and **Spring Cloud**.  
It uses:
- **Eureka Server** for service discovery
- **Auth Service** for authentication and JWT token generation
- **API Gateway** for routing and security enforcement

All secured requests use **JWT Bearer Tokens**.

---

## 🧩 Microservices Architecture Flow (with Auth & JWT)

```text
Client (Web / Mobile)
        |
        | 1. Login Request (username/password)
        v
-----------------------
|   Auth Service      |
|  (JWT Generator)   |
-----------------------
        |
        | 2. JWT Token
        v
Client (stores token)
        |
        | 3. Request with Header:
        |    Authorization: Bearer <JWT>
        v
-----------------------
|   API Gateway      |
-----------------------
        |
        | 4. Token Validation
        v
-----------------------
|   Eureka Server    |
-----------------------
        |
        v
------------------------------------------------
| Order Service | Payment Service | Inventory  |
| Notification Service                          |
------------------------------------------------
        |
        v
   Database (per service)
```
---
- **Lombok** generates mutable classes and hides behavior through annotations.
- **Records** are part of the Java language, immutable by default, and safer for concurrent systems.

---
## Exception Handler
![ExceptionTest](docs/exception_test.png)

## Hibernate Mapping
OneToOne OneOrder->OnePayment 
OneToMany OneOrder->ManyItems

Order - inverse side,mappedBy=<FK name>
Payment - Owning side with FK
Items -Owning side with FK

Infinite recursion happens when Jackson serializes bidirectional JPA relationships.
The fix is to control JSON serialization using @JsonManagedReference and @JsonBackReference, or better, return DTOs instead of entities.
## DTO
We never expose entities directly. We use DTOs to avoid recursion, lazy loading issues, and overfetching.

![CreateOder](docs/create_order.png)

### SQL queries forming:
Hibernate: insert into orders (order_number,totalamount,id) values (?,?,default)
Hibernate: insert into order_item (order_id,price,product_name,quantity,id) values (?,?,?,?,default)
Hibernate: insert into order_item (order_id,price,product_name,quantity,id) values (?,?,?,?,default)
Hibernate: select next value for order_payment_seq
Hibernate: insert into order_payment (order_id,payment_mode,payment_status,id) values (?,?,?,?)

To check on h2 DB
http://localhost:8081/h2-console

![H2 Console](docs/h2_console.png)