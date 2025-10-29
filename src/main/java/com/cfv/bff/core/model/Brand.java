package com.cfv.bff.core.model;

import com.cfv.bff.core.model.valueobject.NonNullableStringValueObject;

public class Brand extends NonNullableStringValueObject {

    protected Brand(String value) {
        super(value);
        validateBrand(value);
    }

    private void validateBrand(String value) {
        String trimmed = value.trim();
        if (trimmed.length() < 2 || trimmed.length() > 50) {
            throw new IllegalArgumentException("Invalid brand name length: " + value);
        }
        if (!trimmed.matches("^[A-Za-z0-9\\s\\-_.]+$")) {
            throw new IllegalArgumentException("Invalid characters in brand name: " + value);
        }
    }

    public static Brand fromPrimitive(String value) {
        return new Brand(value);
    }
}

