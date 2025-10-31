package com.cfv.bff.infrastructure.controller;
import com.cfv.bff.infrastructure.exceptions.DeviceNotFoundException;
import com.cfv.bff.infrastructure.exceptions.ErrorSavingDeviceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class DevicesInfoExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<String> handleDeviceNotFoundException(DeviceNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleErrorSavingDeviceException(ErrorSavingDeviceException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
