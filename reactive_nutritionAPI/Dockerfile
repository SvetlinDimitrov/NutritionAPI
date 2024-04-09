FROM openjdk:17-alpine

WORKDIR /app

COPY target/reactive_nutritionAPI-0.0.1-SNAPSHOT.jar /app/reactive_nutritionAPI.jar

EXPOSE 8080

CMD ["java", "-jar", "reactive_nutritionAPI.jar"]
