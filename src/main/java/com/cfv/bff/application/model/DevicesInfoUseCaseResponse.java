package com.cfv.bff.application.model;

import com.cfv.bff.core.model.DevicesInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DevicesInfoUseCaseResponse {
    private final List<DevicesInfo> devicesInfoList;
    private final Integer totalLines;
    private final Integer totalLinesFiltered;
}
