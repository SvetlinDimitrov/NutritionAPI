package com.example.nutritionapi.exceptions;

public class RecordNotFound  extends Exception{
    public RecordNotFound(String day) {
        super("Record with the given day " + day + " does not existed in the data.");

    }

}
