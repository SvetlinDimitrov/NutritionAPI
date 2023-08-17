package com.example.nutritionapi.exceptions;

public class MacronutrientNotFound extends Exception{
    public MacronutrientNotFound(String name, String electrolyteNames) {
        super("Electrolyte with name '" + name + "' does not existed in the data.\n" +
                "The available search names are: "+electrolyteNames);

    }
}
