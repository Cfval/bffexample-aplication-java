package com.cfv.bff.infrastructure.controller;

import com.cfv.bff.application.GETAllDevicesInfoUseCase;
import com.cfv.bff.application.dto.DevicesInfoDto;
import com.cfv.bff.application.mappers.DevicesInfoDtoMapper;
import com.cfv.bff.application.model.DevicesInfoUseCaseResponse;
import com.cfv.bff.infrastructure.controller.dto.DevicesInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GETAllDevicesInfoRestController {
    private final GETAllDevicesInfoUseCase useCase;

    @Operation(summary = "Get all info from devices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices found successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, invalid parameters",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })

    @GetMapping("/alldevices")
    public ResponseEntity<DevicesInfoResponse> findAll() {

        DevicesInfoUseCaseResponse devicesInfoList = useCase.invoke();

        List<DevicesInfoDto> dtoList = devicesInfoList.getDevicesInfoList().stream()
                .map(DevicesInfoDtoMapper::fromDomain)
                .toList();

        return ResponseEntity.ok(new DevicesInfoResponse(dtoList, devicesInfoList.getTotalLines(), devicesInfoList.getTotalLinesFiltered()));
    }
}

