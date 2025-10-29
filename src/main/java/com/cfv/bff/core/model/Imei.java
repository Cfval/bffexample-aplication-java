package com.cfv.bff.core.model;

import com.cfv.bff.core.model.valueobject.NonNullableStringValueObject;

import java.util.Optional;

public class Imei extends NonNullableStringValueObject {

    public static final String IMEI_PATTERN = "^\\d{9}$";

    protected Imei(String value) {
        super(value);
        validateFormat(value);
    }

    private void validateFormat(String value) {
        Optional.ofNullable(value)
                .map(String::trim)
                .filter(v -> v.matches(IMEI_PATTERN))
                .orElseThrow(() -> new IllegalArgumentException("Invalid IMEI format: " + value));
    }

    public static Imei fromPrimitive(String value) {
        return new Imei(value);
    }
}

