package com.api.reactive_nutritionapi.exceptions;

public class RecordNotFoundException extends Exception{
    public RecordNotFoundException(String day) {

        super("Record with the given id " + day + " does not existed in the data.");

    }

}
