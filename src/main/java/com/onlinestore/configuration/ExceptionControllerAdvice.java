package com.onlinestore.configuration;


import com.onlinestore.exceptions.*;
import com.onlinestore.utils.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Class used to handle exceptions from application
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = {ResourceMissingInDatabase.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceMissingInDatabase(ResourceMissingInDatabase ex) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {ResourceAlreadyPresentInDatabase.class})
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorMessage resourceAlreadyPresentInDatabase(ResourceAlreadyPresentInDatabase ex){
        return new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {InsufficientStockException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage inssuficientStockException(InsufficientStockException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {EmptyShoppingCartException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage emptyShoppingCartException(EmptyShoppingCartException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {CategoryAssociatedProductsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage categoryAssociatedProductsException(CategoryAssociatedProductsException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

}
