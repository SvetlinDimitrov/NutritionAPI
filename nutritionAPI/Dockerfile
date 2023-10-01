# Use an official OpenJDK runtime as the base image
FROM openjdk

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/nutritionAPI-0.0.1-SNAPSHOT.jar ./

# Specify the command to run the JAR file
CMD ["java", "-jar", "nutritionAPI-0.0.1-SNAPSHOT.jar"]