package com.pro.anat.validation.string_validations;

import com.pro.anat.validation.Validation;

public class StringLengthValidation<T> implements Validation<String, T> {
    private String value;
    private T error;
    private int min;
    private int max;
    private boolean isOptional;

    public StringLengthValidation(Builder<T> builder) {
        this.value = builder.value;
        this.error = builder.error;
        this.min = builder.min;
        this.max = builder.max;
        this.isOptional = builder.mOptional;
    }

    @Override
    public boolean isValid() {
        if (isOptional) {
            return value == null || (value.trim().length() <= 0) || (value.length() >= min && value.length() <= max);
        } else {
            return (value != null) && !value.isEmpty() && (value.length() >= min && value.length() <= max);
        }
    }

    @Override
    public T getError() {
        return error;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String actual) {
        this.value = actual;
    }

    public static class Builder<T> {
        private String value;
        private T error;
        private int min;
        private int max;
        private boolean mOptional;

        public Builder() {
            value = "";
            min = 1;
            max = 1;
        }

        public Builder<T> setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder<T> setError(T error) {
            this.error = error;
            return this;
        }

        public Builder<T> setMin(int min) {
            this.min = min;
            return this;
        }

        public Builder<T> setMax(int max) {
            this.max = max;
            return this;
        }

        public Builder<T> setOptional(boolean optional) {
            mOptional = optional;
            return this;
        }

        public Validation<String, T> build() {
            return new StringLengthValidation<>(this);
        }

    }
}
