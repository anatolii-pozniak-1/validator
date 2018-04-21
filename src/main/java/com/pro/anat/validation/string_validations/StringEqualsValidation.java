package com.pro.anat.validation.string_validations;

import com.pro.anat.validation.Validation;

public class StringEqualsValidation<T> implements Validation<String, T> {
    private String actual;
    private final String expected;
    private final T error;
    private final boolean isOptional;

    private StringEqualsValidation(Builder<T> builder) {
        this.actual = builder.actual;
        this.error = builder.error;
        this.isOptional = builder.isOptional;
        this.expected = builder.expected;
    }

    @Override
    public boolean isValid() {
        if (isOptional) {
            return actual == null || (actual.trim().length() <= 0) ||
                    expected == null || (expected.trim().length() <= 0) ||
                    actual.equals(expected);
        }
        return (actual != null && !actual.isEmpty()) &&
                (expected != null && !expected.isEmpty()) &&
                expected.equals(actual);
    }

    @Override
    public void setValue(String actual) {
        this.actual = actual;
    }

    @Override
    public T getError() {
        return error;
    }

    @Override
    public String getValue() {
        return actual;
    }


    public static class Builder<T> {
        private String actual;
        private String expected;

        private T error;
        private final boolean isOptional;

        public Builder(boolean isOptional) {
            this.isOptional = isOptional;
        }

        public Builder<T> setValues(String... values) {
            this.actual = values != null && values.length > 1 ? values[0] : "";
            this.expected = values != null && values.length > 2 ? values[1] : "";
            return this;
        }

        public Builder<T> setError(T error) {
            this.error = error;
            return this;
        }

        public Validation<String, T> build() {
            return new StringEqualsValidation<>(this);
        }
    }
}
