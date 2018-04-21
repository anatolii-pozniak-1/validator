package com.pro.anat.validation.string_validations;

import com.pro.anat.validation.Validation;

public class StringEmptyValidation<T> implements Validation<String, T> {
    private String value;
    private T error;
    private boolean isOptional;

    public StringEmptyValidation(Builder<T> builder) {
        this.value = builder.value;
        this.error = builder.error;
        this.isOptional = builder.isOptional;
    }

    @Override
    public boolean isValid() {
        if (isOptional) {
            return value == null || (value.trim().length() <= 0);
        }
        return (value != null && !value.isEmpty());
    }

    @Override
    public void setValue(String actual) {
        this.value = actual;
    }

    @Override
    public T getError() {
        return error;
    }

    @Override
    public String getValue() {
        return value;
    }


    public static class Builder<T> {
        private String value;
        private T error;
        private final boolean isOptional;

        public Builder(boolean isOptional) {
            this.isOptional = isOptional;
        }

        public Builder<T> setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder<T> setError(T error) {
            this.error = error;
            return this;
        }

        public Validation<String, T> build() {
            return new StringEmptyValidation<>(this);
        }
    }
}
