# ================================
# Stage 1: Build the Spring Boot JAR
# ================================
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# ================================
# Stage 2: Run the JAR
# ================================
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy only the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
