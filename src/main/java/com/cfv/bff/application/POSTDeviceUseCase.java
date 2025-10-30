package com.cfv.bff.application;

import com.cfv.bff.application.dto.DevicesInfoDto;
import com.cfv.bff.application.mappers.DevicesInfoDtoMapper;
import com.cfv.bff.core.model.DevicesInfo;
import com.cfv.bff.core.ports.DevicesInfoRepository;
import com.cfv.bff.infrastructure.mappers.DevicesInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class POSTDeviceUseCase {

    private final DevicesInfoRepository repository;

    public DevicesInfoDto invoke(DevicesInfoDto device) {
        DevicesInfo deviceToSave = DevicesInfoMapper.toDomain(device); // Cambia a domino, id null
        DevicesInfo savedDevice = repository.save(deviceToSave); // Guarda en el repositorio
        return DevicesInfoDtoMapper.fromDomain(savedDevice); // Convierte de nuevo a DTO y lo devuelve con el id generado
    }
}
