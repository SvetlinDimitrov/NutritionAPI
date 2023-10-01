CREATE SCHEMA IF NOT EXISTS nutritionApi;

USE nutritionApi;

CREATE TABLE IF NOT EXISTS `users` (
    `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `age` int DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `gender` enum('FEMALE','MALE') DEFAULT NULL,
    `height` decimal(38,2) DEFAULT NULL,
    `kilograms` decimal(38,2) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `user_details` enum('COMPLETED','NOT_COMPLETED') DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    `workout_state` enum('LIGHTLY_ACTIVE','MODERATELY_ACTIVE','SEDENTARY','SUPER_ACTIVE','VERY_ACTIVE') DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `records` (
    `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `daily_calories` decimal(38,2) DEFAULT NULL,
    `user_id` bigint DEFAULT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE IF NOT EXISTS `nutrition_intake` (
    `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `daily_consumed` decimal(38,2) DEFAULT NULL,
    `lower_bound_intake` decimal(38,2) DEFAULT NULL,
    `measurement` varchar(255) DEFAULT NULL,
    `nutrient_name` varchar(255) DEFAULT NULL,
    `nutrient_type` varchar(255) DEFAULT NULL,
    `upper_bound_intake` decimal(38,2) DEFAULT NULL,
    `record_id` bigint DEFAULT NULL,
    FOREIGN KEY (`record_id`) REFERENCES `records` (`id`)
);
