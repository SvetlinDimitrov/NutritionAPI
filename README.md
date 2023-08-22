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
+ Java JDK (version 17)
+ Spring Boot (version 3.1.2)
+ Spring Boot (dependency-management version 1.1.2)
+ spring-boot-starter-data-jpa
+ spring-boot-starter-security
+ spring-boot-starter-web
+ spring-boot-starter-validation
+ java-jwt:4.4.0
+ mysql-connector-j
+ hsqldb (Integration Tests)

## Usage
If you don't have registration, you can still access information on all types of vitamins, electrolytes, and the basic macronutrients. This includes details on how much you need to consume and everything about them.

Then, when you successfully register and log in, you will be able to modify your profile. If your profile is fully completed (there are no null fields when you access the details endpoint), the program will create a record for you that includes all nutrients, how much you've consumed, how much more you need to take, and more. Everything is calculated based on the fulfilled information.

You can visit your record, add consumed nutrition to it, start a new record, or even delete one. This API uses JWT tokens for authorization."

## API Documentation
[Full Documentation](https://documenter.getpostman.com/view/26519722/2s9Y5U1Qv7#b0047059-4965-448a-a3c7-34e758e58193)https://documenter.getpostman.com/view/26519722/2s9Y5U1Qv7#b0047059-4965-448a-a3c7-34e758e58193

## Contributing
There are a few TODOs that I haven't managed to resolve.

