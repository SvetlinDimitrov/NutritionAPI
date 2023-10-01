FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/*.jar
COPY ./build/libs/nutritionAPI-0.0.1-SNAPSHOT-plain.jar app.jar
ENTRYPOINT ["java", "-jar" , "/app.jar"]