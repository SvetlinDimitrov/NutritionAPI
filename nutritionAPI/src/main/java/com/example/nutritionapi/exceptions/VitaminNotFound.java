package com.example.nutritionapi.exceptions;

public class VitaminNotFound extends Exception{

    public VitaminNotFound(String name, String vitaminNames) {
        super("Vitamin with name " + name + " does not existed in the data.\n" +
                "The available search names are: "+vitaminNames);

    }
}
