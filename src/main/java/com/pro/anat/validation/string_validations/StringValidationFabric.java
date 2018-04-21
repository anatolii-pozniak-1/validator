package com.pro.anat.validation.string_validations;

import com.pro.anat.validation.Validation;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by anatolii.pozniak on 3/22/18.
 */
@Singleton
public class StringValidationFabric<E> {

    private static final String PATTERN_ONLY_D_AND_W = "(\\w|\\d)+";
    private static final String PATTERN_ONLY_TEXT = "^[a-zA-Z ]*$";
    private static final String PATTERN_EMAIL = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private final StringErrorResource<E> mStringErrorResource;

    @Inject
    public StringValidationFabric(StringErrorResource<E> stringErrorResource) {
        mStringErrorResource = stringErrorResource;
    }

    public Validation<String, E> create(Type type, String... values) {
        switch (type) {
            case CITY:
                return new StringComplexValidation.Builder<E>(false)
                        .setValue(values[0])
                        .addStringEmptyValidation(mStringErrorResource.getEmptyError())
                        .addStringLengthValidation(mStringErrorResource.getLengthError(), 2, 20)
                        .build();
            case STATE:
                return new StringComplexValidation.Builder<E>(false)
                        .setValue(values[0])
                        .addStringEmptyValidation(mStringErrorResource.getEmptyError())
                        .addStringLengthValidation(mStringErrorResource.getLengthError(), 2, 20)
                        .build();
            case NAME:
                return new StringComplexValidation.Builder<E>(false)
                        .setValue(values[0])
                        .addStringEmptyValidation(mStringErrorResource.getEmptyErrorName())
                        .addStringLengthValidation(mStringErrorResource.getLengthErrorName(), 2, 20)
                        .addStringPatternValidation(mStringErrorResource.getPatternErrorName(), PATTERN_ONLY_TEXT)
                        .build();
            case USER_AGE:
                return new StringComplexValidation.Builder<E>(false)
                        .setValue(values[0])
                        .addStringEmptyValidation(mStringErrorResource.getEmptyErrorAge())
                        .addStringLengthValidation(mStringErrorResource.getLengthErrorAge(), 2, 2)
                        .build();
            case USER_NAME:
                return new StringComplexValidation.Builder<E>(false)
                        .setValue(values[0])
                        .addStringEmptyValidation(mStringErrorResource.getEmptyErrorUserName())
                        .addStringLengthValidation(mStringErrorResource.getLengthErrorUserName(), 6, 50)
                        .addStringPatternValidation(mStringErrorResource.getPatternErrorUserName(), PATTERN_ONLY_D_AND_W)
                        .build();
            case TELEPHONE:
                return new StringEmptyValidation.Builder<E>(false)
                        .setValue(values[0])
                        .setError(mStringErrorResource.getEmptyPhone())
                        .build();
            case PASSWORD:
                return new StringComplexValidation.Builder<E>(false)
                        .setValue(values[0])
                        .addStringEmptyValidation(mStringErrorResource.getErrorPassword())
                        .addStringLengthValidation(mStringErrorResource.getLengthErrorPassword(), 8, 50)
                        .build();
            case REFERRAL:
                return new StringPatternValidation.Builder<E>(false)
                        .setValue(values[0])
                        .setError(mStringErrorResource.getPatternErrorReferral())
                        .setPattern(PATTERN_ONLY_D_AND_W)
                        .build();
            case EMAIL:
                return new StringComplexValidation.Builder<E>(false)
                        .setValue(values[0])
                        .addStringEmptyValidation(mStringErrorResource.getEmptyErrorEmail())
                        .addStringPatternValidation(mStringErrorResource.getPatternErrorEmail(), PATTERN_EMAIL)
                        .build();
            case CONFIRM_PASSWORD:
                return new StringEqualsValidation.Builder<E>(false)
                        .setValues(values)
                        .setError(mStringErrorResource.getErrorConfirm())
                        .build();
            default:
                return new StringEmptyValidation.Builder<E>(true)
                        .setValue(values[0])
                        .setError(mStringErrorResource.getEmptyError())
                        .build();

        }
    }

    public enum Type {
        EMAIL, PASSWORD, EMPTY, OPTIONAL, LENGTH, USER_NAME, TELEPHONE, REFERRAL, USER_AGE, NAME, CITY, STATE, CONFIRM_PASSWORD
    }


}
