package com.cfv.bff.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class DevicesInfoDto {
    private final Long id;
    private final String brand;
    private final String model;
    private final String imei;
}
