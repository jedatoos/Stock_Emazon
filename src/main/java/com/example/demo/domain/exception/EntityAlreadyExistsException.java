package com.example.demo.domain.exception;


import com.example.demo.domain.util.ExceptionMessagesConstants;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String entityName) {
        super(entityName + " " + ExceptionMessagesConstants.ENTITY_ALREADY_EXISTS.getMessage());
    }


}
