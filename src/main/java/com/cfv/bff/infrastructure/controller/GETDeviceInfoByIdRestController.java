package com.cfv.bff.infrastructure.controller;

import com.cfv.bff.application.GETDeviceInfoByIdUseCase;
import com.cfv.bff.application.dto.DevicesInfoDto;
import com.cfv.bff.application.mappers.DevicesInfoDtoMapper;
import com.cfv.bff.application.model.DeviceInfoByIdUseCaseResponse;
import com.cfv.bff.infrastructure.controller.dto.DeviceInfoByIdResponse;
import com.cfv.bff.infrastructure.exceptions.DeviceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device")
public class GETDeviceInfoByIdRestController {

    private final GETDeviceInfoByIdUseCase useCase;

    @Operation(summary = "Get device info by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device found successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, invalid id",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Device not found",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<DeviceInfoByIdResponse> findById(@PathVariable Long id) {
        try {
            DeviceInfoByIdUseCaseResponse response = useCase.invoke(id);
            DevicesInfoDto deviceDto = DevicesInfoDtoMapper.fromDomain(response.getDevice());
            return ResponseEntity.ok(new DeviceInfoByIdResponse(deviceDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e) {
            throw new DeviceNotFoundException("Device not found with ID: " + id);
        }
    }
}



