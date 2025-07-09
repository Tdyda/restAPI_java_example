# Dockerfile.app
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the built jar (assumes you ran: ./mvnw clean package)
COPY target/*.jar app.jar

# Expose the app port
EXPOSE 5001

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
