package com.cfv.bff.core.model.valueobject;

public class NullableStringValueObject extends ValueObject<String> {
    protected NullableStringValueObject(String value) {
        super(value);
    }

    protected String validate(String value) {
        return value;
    }
}
