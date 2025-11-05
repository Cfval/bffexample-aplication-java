package com.cfv.bff.core;

import com.cfv.bff.core.model.DevicesInfo;

import java.util.List;

public final class DevicesInfoMother {

    private DevicesInfoMother() { /* utilitaria */ }

    /**
     * Crea un DevicesInfo sin id (nuevo recurso), usando primitivas.
     */
    public static DevicesInfo newDevice(String brand, String model, String imei) {
        return DevicesInfo.fromPrimitives(brand, model, imei);
    }

    /**
     * Crea un DevicesInfo con id (simula entidad persistida).
     */
    public static DevicesInfo persistedDevice(Long id, String brand, String model, String imei) {
        return DevicesInfo.fromPrimitives(id, brand, model, imei);
    }

    /**
     * Dispositivo de ejemplo 1 (persistido).
     */
    public static DevicesInfo device1() {
        return persistedDevice(1L, "MarcaA", "ModeloA", "123456789");
    }

    /**
     * Dispositivo de ejemplo 2 (persistido).
     */
    public static DevicesInfo device2() {
        return persistedDevice(2L, "MarcaB", "ModeloB", "987654321");
    }

    /**
     * Dispositivo de ejemplo sin id (nuevo).
     */
    public static DevicesInfo newDeviceExample() {
        return newDevice("MarcaX", "ModelY", "222333444");
    }

    /**
     * Lista de dispositivos de ejemplo.
     */
    public static List<DevicesInfo> sampleList() {
        return List.of(device1(), device2());
    }
}


