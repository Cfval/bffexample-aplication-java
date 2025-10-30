package com.cfv.bff.application;

import com.cfv.bff.application.model.DevicesInfoUseCaseResponse;
import com.cfv.bff.core.model.DevicesInfo;
import com.cfv.bff.core.ports.DevicesInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GETAllDevicesInfoUseCase {
    private final DevicesInfoRepository repository;

    public DevicesInfoUseCaseResponse invoke() {

        List<DevicesInfo> devicesInfo = repository.findAll();
        Integer totalLines = devicesInfo.size();
        Integer totalLinesFiltered = devicesInfo.size(); // TODO: Implementar filtros

        return new DevicesInfoUseCaseResponse(devicesInfo, totalLines, totalLinesFiltered);
    }
}
