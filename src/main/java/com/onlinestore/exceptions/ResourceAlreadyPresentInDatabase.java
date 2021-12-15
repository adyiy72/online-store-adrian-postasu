package com.onlinestore.exceptions;

public class ResourceAlreadyPresentInDatabase extends RuntimeException {

    public ResourceAlreadyPresentInDatabase(String message){
        super(message);
    }

}
