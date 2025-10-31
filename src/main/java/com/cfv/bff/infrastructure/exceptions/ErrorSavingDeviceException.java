package com.cfv.bff.infrastructure.exceptions;

public class ErrorSavingDeviceException extends RuntimeException {

    public ErrorSavingDeviceException(String message) {
        super(message);
    }
}