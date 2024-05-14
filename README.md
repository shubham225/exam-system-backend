# Online Exam System - Backend
This is a backend repository for the Online Exam System! This Spring Boot project serves as the backbone for managing and administering online exams.

## Prerequisites
This project is created using Java 17 and Spring boot 3.2.1, maven framework is used as build system.

Before you begin, ensure you have met the following requirements:

- **Java:** Version 17 or later. You can download it from [OpenJDK](https://openjdk.java.net/).
- **Spring Boot:** Version 3.2.1. Check the [official Spring Boot website](https://spring.io/projects/spring-boot) for details.
- **Maven:** The project uses Maven as the build system. Install Maven by following the instructions [here](https://maven.apache.org/install.html).
- **Database**: Set up a MySQL or MariaDB database server. You can install MySQL or MariaDB server locally on your development machine or use a cloud-based database service. You can download MySQL from the [official MySQL website](https://dev.mysql.com/downloads/) or MariaDB from the [official MariaDB website](https://mariadb.org/download/).

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

Make sure to set these environment variables either directly in your development environment or using a configuration file such as `application.properties` or `application.yml` for local development. Additionally, when deploying your Spring Boot application, you can configure these variables through your deployment environment settings.

## License

This project is licensed under the [MIT License](LICENSE.md).