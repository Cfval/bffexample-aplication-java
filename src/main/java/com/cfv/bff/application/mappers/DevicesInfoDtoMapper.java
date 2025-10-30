package com.cfv.bff.application.mappers;

import com.cfv.bff.application.dto.DevicesInfoDto;
import com.cfv.bff.core.model.DevicesInfo;

public class DevicesInfoDtoMapper {
    private DevicesInfoDtoMapper() {}

    public static DevicesInfoDto fromDomain(DevicesInfo devicesInfo){
        if (devicesInfo == null) {
            return null;
        }

        return new DevicesInfoDto(
                devicesInfo.id(),
                devicesInfo.brand(),
                devicesInfo.model(),
                devicesInfo.imei()
        );
    }
}
