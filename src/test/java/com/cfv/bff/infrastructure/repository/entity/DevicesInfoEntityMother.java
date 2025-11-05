package com.cfv.bff.infrastructure.repository.entity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class DevicesInfoEntityMother {

    private DevicesInfoEntityMother() { /* utilitaria */ }

    /**
     * Crea una entidad nueva (id == null) a partir de primitivas.
     */
    public static DevicesInfoEntity newEntity(String brand, String model, String imei) {
        return new DevicesInfoEntity(null, brand, model, imei);
    }

    /**
     * Crea una entidad simulando que ya está persistida (con id).
     */
    public static DevicesInfoEntity persistedEntity(Long id, String brand, String model, String imei) {
        return new DevicesInfoEntity(id, brand, model, imei);
    }

    /**
     * Entidad de ejemplo 1 (persistida).
     */
    public static DevicesInfoEntity entity1() {
        return persistedEntity(1L, "MarcaA", "ModeloA", "111111111");
    }

    /**
     * Entidad de ejemplo 2 (persistida).
     */
    public static DevicesInfoEntity entity2() {
        return persistedEntity(2L, "MarcaB", "ModeloB", "222222222");
    }

    /**
     * Entidad de ejemplo sin id (nueva).
     */
    public static DevicesInfoEntity newEntityExample() {
        return newEntity("MarcaX", "ModelY", "333333333");
    }

    /**
     * Genera una lista de entidades de ejemplo.
     */
    public static List<DevicesInfoEntity> sampleList() {
        return List.of(entity1(), entity2());
    }

    /**
     * Generador simple de IMEI (solo para datos de test; no garantiza validez real).
     */
    public static String randomImei() {
        long n = ThreadLocalRandom.current().nextLong(100_000_000L, 999_999_999L);
        return String.valueOf(n);
    }
}

