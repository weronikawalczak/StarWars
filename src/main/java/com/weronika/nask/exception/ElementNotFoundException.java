package com.weronika.nask.exception;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException(String elementName, int id) {
        super(String.format("%s %s not found", elementName, id));
    }
}
