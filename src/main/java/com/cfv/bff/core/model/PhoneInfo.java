package com.cfv.bff.core.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PhoneInfo {

    private final Brand brand;
    private final Model model;
    private final Imei imei;

    public String brand() {
        return brand.toPrimitive();
    }

    public String model() {
        return model.toPrimitive();
    }

    public String imei() {
        return imei.toPrimitive();
    }

    private PhoneInfo(String brand, String model, String imei) {
        this.brand = Brand.fromPrimitive(brand);
        this.model = Model.fromPrimitive(model);
        this.imei = Imei.fromPrimitive(imei);
    }

    public static PhoneInfo fromPrimitives(String brand, String model, String imei) {
        return new PhoneInfo(brand, model, imei);
    }
}
