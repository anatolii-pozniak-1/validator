package com.pro.anat.validation.string_validations;

import com.pro.anat.validation.Validation;

import java.util.regex.Pattern;

public class StringPatternValidation<T> implements Validation<String, T> {

    private String value;
    private T error;
    private String pattern;
    private boolean isOptional;

    public StringPatternValidation(String value, T error, String pattern, boolean isOptional) {
        this.value = value;
        this.error = error;
        this.pattern = pattern;
        this.isOptional = isOptional;
    }

    @Override
    public boolean isValid() {
        if (isOptional) {
            return value == null || (value.trim().length() <= 0) || Pattern.matches(pattern, value);
        } else {
            return Pattern.matches(pattern, value);
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
        private String pattern;
        private final boolean isOptional;

        public Builder(boolean isOptional) {
            this.isOptional = isOptional;
        }

//        public Builder(String value, boolean isOptional) {
//            this.value = value;
//            pattern = "";
//        }

        public Builder<T> setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder<T> setError(T error) {
            this.error = error;
            return this;
        }

        public Builder<T> setPattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        public Validation<String, T> build() {
            return new StringPatternValidation<>(value, error, pattern, isOptional);
        }
    }
}
