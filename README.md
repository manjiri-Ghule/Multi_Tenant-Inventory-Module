# Multi_Tenant-Inventory-Module
This is a multi-tenant Inventory module built using Spring Boot following clean architecture principles



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
X-Tenant-Id: t1

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
