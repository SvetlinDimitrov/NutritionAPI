package com.example.nutritionapi.exceptions;

public class ElectrolyteNotFound extends Exception{
    public ElectrolyteNotFound(String name, String electrolyteNames) {
        super("Electrolyte with name '" + name + "' does not existed in the data.\n" +
                "The available search names are: "+electrolyteNames);

    }
}
