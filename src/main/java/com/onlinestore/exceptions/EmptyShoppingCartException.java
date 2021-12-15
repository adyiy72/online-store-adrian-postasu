package com.onlinestore.exceptions;

public class EmptyShoppingCartException extends RuntimeException{

    public EmptyShoppingCartException(String msg){
        super(msg);
    }
}
