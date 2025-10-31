package com.cfv.bff.infrastructure.repository;

import com.cfv.bff.infrastructure.repository.entity.DevicesInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDevicesInfoRepository extends JpaRepository<DevicesInfoEntity, Long> {
}
