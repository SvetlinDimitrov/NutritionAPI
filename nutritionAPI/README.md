# NutritionAPI

## Introduction
This is a nutrition API. You can obtain information about your daily consumption of vitamins, macros, and electrolytes. This API is perfect for someone who wants to track their nutrition.

## Features
You can get information about functions, sources, types, and daily intake of the given nutrient. Types of nutrients include:
+ Vitamins
+ Macronutrients
+ Electrolytes

## Getting Started
All you need to do is clone my repository and run the project.Nothing more;
+ https://github.com/SvetlinDimitrov/NutritionAPI.git

## Prerequisites
+ Java JDK (version 21)

## Usage
If you don't have registration, you can still access information on all types of vitamins, electrolytes, and the basic macronutrients. This includes details on how much you need to consume and everything about them.

Then, when you successfully register and log in, you will be able to modify your profile. If your profile is fully completed (there are no null fields when you access the details endpoint), the program will create a record for you that includes all nutrients, how much you've consumed, how much more you need to take, and more. Everything is calculated based on the fulfilled information.

You can visit your record, add consumed nutrition to it, start a new record, or even delete one. This API uses JWT tokens for authorization."

## API Documentation
The API documentation is generated using SpringDoc OpenAPI. You can view the API documentation 
by navigating to the following URL in your web browser: http://localhost:8080/swagger-ui.html

## Running the Application with Docker Compose

To run both the database and the application simultaneously, you can use the provided `docker-compose.yml` file. This file sets up the necessary services and configurations.

### Steps to Run

1. Ensure you have Docker and Docker Compose installed on your machine.
2. Navigate to the directory containing the `docker-compose.yml` file.
3. Run the following command to start the services:

   ```sh
   docker-compose up
   ```

This command will start both the MySQL database and the Spring Boot application. The application will be accessible at `http://localhost:8080`.