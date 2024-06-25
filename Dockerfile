# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the jar file to the container
COPY target/todo_app-1.0-SNAPSHOT.jar /app/todo.jar
COPY config.yml /app/config.yml

# Expose the port that the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "todo.jar", "server", "config.yml"]
