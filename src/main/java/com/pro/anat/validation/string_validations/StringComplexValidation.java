package com.pro.anat.validation.string_validations;

import com.pro.anat.validation.Validation;

import java.util.ArrayList;
import java.util.List;

public class StringComplexValidation<T> implements Validation<String, T> {
    private final List<Validation<String, T>> validationList;
    private String mValue;
    private T error;

    private StringComplexValidation(Builder<T> builder) {
        mValue = builder.mValue;
        validationList = builder.validationList;
    }

    public boolean isValid() {
        for (Validation<String, T> validation : validationList) {
            if (!validation.isValid()) {
                error = validation.getError();
                return false;
            }
        }
        return true;
    }

    @Override
    public void setValue(String actual) {
        mValue = actual;
    }

    public T getError() {
        return error;
    }

    @Override
    public String getValue() {
        return mValue;
    }

    public static class Builder<T> {
        private final boolean isOptional;
        private final List<Validation<String, T>> validationList;
        private String mValue;

        public Builder(boolean isOptional) {
            this.isOptional = isOptional;
            validationList = new ArrayList<>();
        }

        public Builder<T> setValue(String value) {
            this.mValue = value;
            return this;
        }

        public Builder<T> addStringEmptyValidation(T error) {
            validationList.add(new StringEmptyValidation.Builder<T>(isOptional)
                    .setValue(mValue)
                    .setError(error)
                    .build());
            return this;
        }

        public Builder<T> addStringPatternValidation(T error, String pattern) {
            validationList.add(new StringPatternValidation.Builder<T>(isOptional)
                    .setError(error)
                    .setPattern(pattern)
                    .setValue(mValue)
                    .build());
            return this;
        }

        public Builder<T> addStringLengthValidation(T error, int min, int max) {
            validationList.add(new StringLengthValidation.Builder<T>()
                    .setError(error)
                    .setMax(max)
                    .setMin(min)
                    .setOptional(isOptional)
                    .setValue(mValue)
                    .build());
            return this;
        }

        public StringComplexValidation<T> build() {
            return new StringComplexValidation<>(this);
        }
    }
}
