package com.cfv.bff.core.ports;

import com.cfv.bff.core.model.DevicesInfo;

import java.util.List;
import java.util.Optional;

public interface DevicesInfoRepository {
    List<DevicesInfo> findAll();
    Optional<DevicesInfo> findById(Long id);
    DevicesInfo save(DevicesInfo device);            // guarda uno
    List<DevicesInfo> saveAll(List<DevicesInfo> devices); // guarda varios
}
