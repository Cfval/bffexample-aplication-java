package com.cfv.bff.application;

import com.cfv.bff.application.model.DeviceInfoByIdUseCaseResponse;
import com.cfv.bff.core.model.DevicesInfo;
import com.cfv.bff.core.ports.DevicesInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GETDeviceInfoByIdUseCase {

    private final DevicesInfoRepository repository;

    public DeviceInfoByIdUseCaseResponse invoke(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        DevicesInfo device = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found with ID: " + id));

        return new DeviceInfoByIdUseCaseResponse(device);
    }
}
