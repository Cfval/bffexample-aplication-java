package com.cfv.bff.infrastructure.repository.adapter;

import com.cfv.bff.core.model.DevicesInfo;
import com.cfv.bff.core.ports.DevicesInfoRepository;
import com.cfv.bff.infrastructure.mappers.DevicesInfoMapper;
import com.cfv.bff.infrastructure.repository.JpaDevicesInfoRepository;
import com.cfv.bff.infrastructure.repository.entity.DevicesInfoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DevicesInfoRepositoryAdapter implements DevicesInfoRepository {

    private final JpaDevicesInfoRepository jpaDevicesInfoRepository;

    @Override
    public List<DevicesInfo> findAll() {
        return jpaDevicesInfoRepository.findAll().stream()
                .map(DevicesInfoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DevicesInfo> findById(Long id) {
        return jpaDevicesInfoRepository.findById(id)
                .map(DevicesInfoMapper::toDomain);
    }

    @Override
    public DevicesInfo save(DevicesInfo device) {
        DevicesInfoEntity entity = DevicesInfoMapper.toEntity(device);
        DevicesInfoEntity savedEntity = jpaDevicesInfoRepository.save(entity);
        return DevicesInfoMapper.toDomain(savedEntity);
    }

}