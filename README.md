# Task Management System

## Запуск проекта локально

1. Убедитесь, что у вас установлен Docker.
2. Запустите базу данных с помощью Docker Compose:
   docker-compose up -d
3. Запустите приложение Spring Boot:
   ./mvnw spring-boot:run
4. Откройте Swagger UI для тестирования API:
   [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)