package com.onlinestore.exceptions;

public class ResourceMissingInDatabase extends RuntimeException{

    public ResourceMissingInDatabase(String message){
        super(message);
    }
}
