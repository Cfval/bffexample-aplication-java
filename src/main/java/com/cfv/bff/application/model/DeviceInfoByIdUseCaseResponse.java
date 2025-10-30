package com.cfv.bff.application.model;

import com.cfv.bff.core.model.DevicesInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public class DeviceInfoByIdUseCaseResponse {
    private final DevicesInfo device;
}
