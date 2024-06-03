# Online Exam System - Backend
This is a backend repository for the Online Exam System! This Spring Boot project serves as the backbone for managing and administering online exams.

## Prerequisites
This project is created using Java 17 and Spring boot 3.2.1, maven framework is used as build system.

Before you begin, ensure you have met the following requirements:

- **Java:** Version 17 or later. You can download it from [OpenJDK](https://openjdk.java.net/).
- **Spring Boot:** Version 3.2.1. Check the [official Spring Boot website](https://spring.io/projects/spring-boot) for details.
- **Maven:** The project uses Maven as the build system. Install Maven by following the instructions [here](https://maven.apache.org/install.html).
- **Database**: Set up a MySQL or MariaDB database server. You can install MySQL or MariaDB server locally on your development machine or use a cloud-based database service. You can download MySQL from the [official MySQL website](https://dev.mysql.com/downloads/) or MariaDB from the [official MariaDB website](https://mariadb.org/download/).

## Features

- User registration: Allows users to create new accounts by providing necessary details.
- User login: Provides authentication mechanisms for registered users to log in securely.
- Role-based access control (RBAC): Implements different user roles with varying levels of access permissions.
- Token-based authentication: Utilizes JSON Web Tokens (JWT) for secure user authentication.
- Password hashing: Ensures user password security by hashing and storing them securely using bCrypt encoder.
- RESTful API: Provides a clean and efficient API for frontend integration.

## Getting Started

To get a local copy up and running, follow these simple steps:

1. Clone the repository.
   ```bash
   git clone https://github.com/shubham225/exam-system-backend.git
2. Navigate to the project directory.
    ```bash
   cd exam-system-backend
3. Build the project using Maven.
    ```bash
   mvn clean install
4. Run the application.
    ```bash
   java -jar target/exam-system-backend.jar
5. The application will now be accessible at http://localhost:9050. Make sure to configure any additional settings or credentials according to your specific use case.

## Environment Variables

To properly configure and run this project, you will need to set up the following environment variables:

- **EXAM_DATASOURCE_URL**: This variable should be set to the URL of your server datasource. For example, if you're using a MySQL/MariaDB database for service, the URL might look like `jdbc:mariadb://localhost:3306/your_database_name`.

- **EXAM_DB_USER**: Set this variable to the username used to access your database.

- **EXAM_DB_PASSWORD**: Set this variable to the password used to access your database.

- **FRONTEND_URL**: Set this variable to the URL of frontend to allow CORS, default value is `http://localhost:3000`.

Make sure to set these environment variables either directly in your development environment or using a configuration file such as `application.properties` or `application.yml` for local development. Additionally, when deploying your Spring Boot application, you can configure these variables through your deployment environment settings.

## API Endpoints
Here are some of the key API endpoints provided by the online-exam system backend:
### Authentication Endpoints
- **`POST /api/V1/auth/login`:** Authenticate a user and generate a JWT token.Requires providing valid credentials (email and password) in the request body.
- **`POST /api/V1/auth/register`:** Register a new user to participate in the exam.

### Admin Endpoints
- **`GET /api/V1/admin/exam`:** Get details of all available exams.
- **`POST /api/V1/admin/exam`:** Creates a new exam.
- **`GET /api/V1/admin/exam/:id`:** Get exam details by ID.
- **`PUT /api/V1/admin/exam/:id`:** Updates exam details by ID.
- **`DELETE /api/V1/admin/exam/:id`:** Deletes exam by ID.
- **`POST /exam/:id/assign`:** Takes list of user Ids and assign exam to those users.
- **`GET /api/V1/admin/result`:** Get Result of all exams.

### Exam Endpoints
- **`GET /api/V1/test/user/:id/exams`:** Get details of all exams assigned to user having id as :id.
- **`GET /api/V1/test/exam/:id/modules`:** Get details of all modules linked to exam having id as :id.
- **`GET /api/V1/test/module/:id/questions`:** Get details of all questions linked to module having id as :id.
- **`GET /api/V1/test/question/:id`:** Get details of question by ID.
- **`PUT /api/V1/test/question/:id`:** Updates user question details by ID.
- **`PUT /api/V1/test/exam/:id`:** Updates user exam details by ID.

### User Endpoints [Admin privileges]
- **`/api/V1/user`:** Get all users details.
- **`/api/V1/user/:id`:** Get user details by ID.

Refer to the [API Documentation](./docs/DOCUMENTATION.md) for a complete list of endpoints and their usage.

## Generating New RSA Certificates
Before getting started, ensure you have OpenSSL installed on your system. If not, you can download and install it from [OpenSSL website](https://www.openssl.org/).
1. Navigate to the certificates directory :
   ```bash
   cd src/main/resources/certs/
2. Run the following command to generate a 2048-bit RSA private key:
   ```bash
   openssl genrsa -out keypair.pem 2048
3. Next, extract the public key from the private key generated in the previous step using the following command:
    ```bash
   openssl rsa -in keypair.pem -pubout -out public.pem

4. For compatibility and ease of use, convert the private key to PKCS#8 format using the following command:
    ```bash
   openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem

5. Above commands will create three files in the `certificates` directory: `keypair.pem`, `public.pem`, and `private.pem`. We don't need `keypair.pem`; this file can be deleted. The other two files will serve as the private and public keys for signing and validating JWT.

## License

This project is licensed under the [MIT License](LICENSE.md).