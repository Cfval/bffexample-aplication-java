package com.cfv.bff.infrastructure.mappers;

import com.cfv.bff.application.dto.DevicesInfoDto;
import com.cfv.bff.core.model.DevicesInfo;

public class DevicesInfoMapper {
    public static DevicesInfo toDomain(DevicesInfoDto deviceDto) {
        if (deviceDto == null) return null;

        return DevicesInfo.fromPrimitives(
                deviceDto.getId(),    // puede ser null para nuevos recursos
                deviceDto.getBrand(),
                deviceDto.getModel(),
                deviceDto.getImei()
        );
    }
}
