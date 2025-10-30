package com.cfv.bff.core.model;

public final class DevicesInfo {

    private Long id;
    private final Brand brand;
    private final Model model;
    private final Imei imei;

    public Long id() { return id; }
    public String brand() { return brand.toPrimitive(); }
    public String model() { return model.toPrimitive(); }
    public String imei() { return imei.toPrimitive(); }

    private DevicesInfo(Long id, String brand, String model, String imei) {
        this.id = id;
        this.brand = Brand.fromPrimitive(brand);
        this.model = Model.fromPrimitive(model);
        this.imei = Imei.fromPrimitive(imei);
    }

    public static DevicesInfo fromPrimitives(String brand, String model, String imei) {
        return new DevicesInfo(null, brand, model, imei);
    }

    public static DevicesInfo fromPrimitives(Long id, String brand, String model, String imei) {
        return new DevicesInfo(id, brand, model, imei);
    }
}
