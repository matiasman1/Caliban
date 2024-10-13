# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and project files into the container
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src

# Grant execute permission on the Gradle wrapper script
RUN chmod +x gradlew

# Build the project using the Gradle wrapper
RUN ./gradlew build -x test

# Expose port 8090 for the app
EXPOSE 8090

# Set the command to run the application
CMD ["java", "-jar", "build/libs/Caliban-0.0.1-SNAPSHOT.jar"]