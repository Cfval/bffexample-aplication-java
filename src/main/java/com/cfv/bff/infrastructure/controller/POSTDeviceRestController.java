package com.cfv.bff.infrastructure.controller;

import com.cfv.bff.application.POSTDeviceUseCase;
import com.cfv.bff.application.dto.DevicesInfoDto;
import com.cfv.bff.infrastructure.exceptions.ErrorSavingDeviceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class POSTDeviceRestController {

    private final POSTDeviceUseCase useCase;

    @Operation(summary = "Add a new device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Device created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, invalid input",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = String.class)))
    })
    @PostMapping("/adddevices")
    public ResponseEntity<DevicesInfoDto> createDevice(@RequestBody DevicesInfoDto deviceDto) {
        try {
            DevicesInfoDto savedDevice = useCase.invoke(deviceDto);
            return ResponseEntity.status(201).body(savedDevice);
        } catch (Exception e) {
            throw new ErrorSavingDeviceException("Error saving device: " + e.getMessage());
        }
    }
}
