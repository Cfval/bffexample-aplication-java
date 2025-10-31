package com.cfv.bff.infrastructure.mappers;

import com.cfv.bff.application.dto.DevicesInfoDto;
import com.cfv.bff.core.model.DevicesInfo;
import com.cfv.bff.infrastructure.repository.entity.DevicesInfoEntity;

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

    public static DevicesInfo toDomain(DevicesInfoEntity entity) {
        if (entity == null) return null;

        return DevicesInfo.fromPrimitives(
                entity.getId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getImei()
        );
    }

    public static DevicesInfoEntity toEntity(DevicesInfo domain) {
        if (domain == null) return null;

        return new DevicesInfoEntity(
                domain.id(),
                domain.brand(),
                domain.model(),
                domain.imei()
        );
    }
}
