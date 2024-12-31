FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/nutritionAPI-0.0.1-SNAPSHOT.jar ./

# Specify the command to run the JAR file
CMD ["java", "-jar", "nutritionAPI-0.0.1-SNAPSHOT.jar"]