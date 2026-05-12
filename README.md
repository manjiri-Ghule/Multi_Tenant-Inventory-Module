# Multi-Tenant-Inventory-Module
# Dealer & Vehicle Inventory Module

## 🚀 Overview
This is a multi-tenant Inventory module built using Spring Boot following clean architecture principles.

## 🧱 Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Spring Security

## 📊 Features
- Dealer Management (CRUD)
- Vehicle Management (CRUD)
- Filtering, Pagination, Sorting
- Multi-Tenant Support (X-Tenant-Id header)
- Premium Dealer Filtering
- Admin Subscription Count API

## 🔐 Multi-Tenant
All APIs require header:
X-Tenant-Id: tenant1

## ▶️ Run Project
1. Open in IDE
2. Run `DealerInventorySystemApplication`
3. Access:
   - APIs → http://localhost:8080
   - H2 → http://localhost:8080/h2-console

## 🔑 H2 Login
- JDBC URL: jdbc:h2:mem:testdb
- User: sa
- Password: (blank)

## 📮 API Endpoints

### Dealers
POST /dealers  
GET /dealers  
GET /dealers/{id}  
PATCH /dealers/{id}  
DELETE /dealers/{id}  

### Vehicles
POST /vehicles?dealerId=UUID  
GET /vehicles  
GET /vehicles/{id}  
PATCH /vehicles/{id}  
DELETE /vehicles/{id}  

### Filters
GET /vehicles?model=&status=&priceMin=&priceMax=

### Premium Vehicles
GET /vehicles?subscription=PREMIUM

### Admin
GET /admin/dealers/countBySubscription

## ⚠️ Validation Rules
- Missing X-Tenant-Id → 400
- Cross-tenant access → blocked




Goal - Project

Build a multi-tenant Inventory module inside a Modular Monolith application that manages dealers and their vehicles.

The module must be designed using clean architecture principles and should clearly separate responsibilities such as controller, service, domain/entity, repository, validation, and security/tenant enforcement.

Data Model
Dealer

id (UUID)

tenant_id

name

email

subscriptionType ∈ {BASIC, PREMIUM}

Vehicle

id (UUID)

tenant_id

dealerId (FK)

model

price (decimal)

status ∈ {AVAILABLE, SOLD}

Required Endpoints
Dealers

POST /dealers

GET /dealers/{id}

GET /dealers (pagination/sort)

PATCH /dealers/{id}

DELETE /dealers/{id}

Vehicles

POST /vehicles

GET /vehicles/{id}

GET /vehicles
Filters:

model

status

priceMin

priceMax
Also support pagination/sort

PATCH /vehicles/{id}

DELETE /vehicles/{id}

Query

GET /vehicles?subscription=PREMIUM
Return vehicles whose dealer has subscriptionType=PREMIUM while still remaining tenant-scoped.

Admin (GLOBAL_ADMIN only)

GET /admin/dealers/countBySubscription
Expected response:

{ "BASIC": n, "PREMIUM": n }

Document whether this count is per-tenant or overall.

Acceptance Checks

Missing X-Tenant-Id → 400

Cross-tenant access blocked → 403

subscription=PREMIUM only returns vehicles for PREMIUM dealers within the caller’s tenant

Admin counts endpoint requires GLOBAL_ADMIN
