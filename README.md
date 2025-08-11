# Exam Service ![Backend](https://img.shields.io/badge/{_}-Backend-16A34A?style=flat-square)

![Java](https://img.shields.io/badge/Java-17-blue?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen?logo=springboot) 
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-C71A36?logo=apachemaven)
[![Database](https://img.shields.io/badge/Database-MySQL%20%7C%20MariaDB-blue.svg?logo=mysql)](https://www.mysql.com/)
![License](https://img.shields.io/badge/License-MIT-yellow?logo=opensourceinitiative)

Exam Service is a Spring Boot–powered backend for creating, managing, and conducting secure online exams. It provides authentication, role-based access control, and exam administration through a clean, RESTful API.

> 🧠 This backend is designed to work with **[Exam Service Frontend](https://github.com/shubham225/exam-system-frontend)** — a React-based web application that provides the exam-taking interface for students and management tools for admins.


## 📌 Tech Stack
- **Language:** Java 17
- **Framework:** Spring Boot 3.2.1
- **Build Tool:** Maven
- **Database:** MySQL / MariaDB
- **Security:** Spring Security with JWT & bCrypt
- **Others:** OpenSSL for RSA key generation


## 🚀 Features
- **User Registration & Login** – Secure sign-up and authentication.
- **Role-Based Access Control (RBAC)** – Different permissions for Admin, Examiner, and Student.
- **Token-Based Authentication** – JSON Web Tokens (JWT) for session security.
- **Password Hashing** – Using bCrypt for safe password storage.
- **Exam Management** – Create, update, assign, and delete exams.
- **Result Management** – Retrieve and manage exam results.
- **RESTful API** – Easy integration with frontend apps like **Exam Service UI**.


## 🛠 Prerequisites
Before starting, ensure you have:
- **Java 17** → [Download OpenJDK](https://openjdk.java.net/)
- **Spring Boot 3.2.1** → [Docs](https://spring.io/projects/spring-boot)
- **Maven** → [Install](https://maven.apache.org/install.html)
- **Database** → MySQL or MariaDB ([MySQL Download](https://dev.mysql.com/downloads/) / [MariaDB Download](https://mariadb.org/download/))


## 📥 Getting Started
1. **Clone the repository**
   ```bash
   git clone https://github.com/shubham225/exam-system-backend.git
   |||
2. **Navigate to the project**
   ```bash
   cd exam-system-backend
   |||
3. **Build the project**
   ```bash
   mvn clean install
   |||
4. **Run the application**
   ```bash
   java -jar target/exam-service.jar
   |||
5. Access at: [http://localhost:9050](http://localhost:9050)


## ⚙ Environment Variables
Set these before running:
- **EXAM_DATASOURCE_URL** → DB connection URL (e.g., `jdbc:mariadb://localhost:3306/db_name`)
- **EXAM_DB_USER** → DB username
- **EXAM_DB_PASSWORD** → DB password
- **FRONTEND_URL** → URL for CORS (default: `http://localhost:3000`)


## 📡 API Endpoints

### Authentication
- `POST /api/V1/auth/login` – Login & get JWT
- `POST /api/V1/auth/register` – Register new user

### Admin
- `GET /api/V1/admin/exam` – List all exams
- `POST /api/V1/admin/exam` – Create exam
- `GET /api/V1/admin/exam/{id}` – Get exam by ID
- `PUT /api/V1/admin/exam/{id}` – Update exam
- `DELETE /api/V1/admin/exam/{id}` – Delete exam
- `POST /api/V1/exam/{id}/assign` – Assign exam to users
- `GET /api/V1/admin/result` – View results

### Exam
- `GET /api/V1/test/user/{id}/exams` – Get user’s exams
- `GET /api/V1/test/exam/{id}/modules` – Get exam modules
- `GET /api/V1/test/module/{id}/questions` – Get module questions
- `GET /api/V1/test/question/{id}` – Get question by ID
- `PUT /api/V1/test/question/{id}` – Update question
- `PUT /api/V1/test/exam/{id}` – Update exam

### Users (Admin Only)
- `GET /api/V1/user` – List all users
- `GET /api/V1/user/{id}` – Get user by ID

📄 **Full API Docs:** [Documentation](./docs/DOCUMENTATION.md)


## 🔑 Generating RSA Certificates
1. Go to certs directory:
   ```bash
   cd src/main/resources/certs/
   |||
2. Generate private key:
   ```bash
   openssl genrsa -out keypair.pem 2048
   |||
3. Extract public key:
   ```bash
   openssl rsa -in keypair.pem -pubout -out public.pem
   |||
4. Convert private key to PKCS#8:
   ```bash
   openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
   |||

This creates:
- `public.pem` → Public key
- `private.pem` → Private key (PKCS#8)
- `keypair.pem` → Can be deleted


## 📜 License
This project is licensed under the [MIT License](LICENSE.md).
