package com.cfv.bff.core.model;

import com.cfv.bff.core.model.valueobject.NullableStringValueObject;

public class Model extends NullableStringValueObject {

    protected Model(String value) {
        super(value);
        validateModel(value);
    }

    private void validateModel(String value) {
        if (value == null) {
            return; // Permitir null sin validaciones
        }

        String trimmed = value.trim();

        if (trimmed.length() > 100) {
            throw new IllegalArgumentException("Model name too long: " + value);
        }

        if (!trimmed.matches("^[A-Za-z0-9\\s\\-_.]*$")) {
            throw new IllegalArgumentException("Invalid characters in model: " + value);
        }
    }

    public static Model fromPrimitive(String value) {
        return new Model(value);
    }
}

