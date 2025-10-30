package com.cfv.bff.infrastructure.controller.dto;

import com.cfv.bff.application.dto.DevicesInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DevicesInfoResponse {
    private final List<DevicesInfoDto> devicesInfoList;
    private final Integer totalLines;
    private final Integer totalLinesFiltered;
}
