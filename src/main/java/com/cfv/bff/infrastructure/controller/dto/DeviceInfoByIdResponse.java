package com.cfv.bff.infrastructure.controller.dto;

import com.cfv.bff.application.dto.DevicesInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceInfoByIdResponse {
    private final DevicesInfoDto device;
}
