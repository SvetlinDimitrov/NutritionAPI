package com.example.nutritionapi.exceptions;

public class RecordNotFoundException extends Exception{
    public RecordNotFoundException(String day) {

        super("Record with the given day " + day + " does not existed in the data.");

    }

}
