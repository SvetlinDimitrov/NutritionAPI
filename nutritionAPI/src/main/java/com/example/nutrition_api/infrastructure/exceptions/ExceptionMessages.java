package com.example.nutrition_api.infrastructure.exceptions;

public enum ExceptionMessages {
  ELECTROLYTE_NOT_FOUND("Electrolyte not found. Searchable: %s", "Electrolyte not found"),
  MACRONUTRIENT_NOT_FOUND("Macronutrient not found. Searchable: %s", "Macronutrient not found"),
  VITAMIN_NOT_FOUND("Vitamin not found. Searchable: %s", "Vitamin not found"),
  RECORD_NOT_FOUND("Record not found", "Record not found"),
  NUTRITION_TO_UPDATE_NOT_FOUND("Nutrition to update not found", "Nutrition to update not found"),
  REFRESH_TOKEN_EXPIRED("Refresh token expired", "Refresh token expired");

  public final String message;
  public final String title;

  ExceptionMessages(String message, String title) {
    this.message = message;
    this.title = title;
  }
}
