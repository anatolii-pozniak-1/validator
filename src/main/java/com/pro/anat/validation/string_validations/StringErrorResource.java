package com.pro.anat.validation.string_validations;

/**
 * Created by Владелец on 18.04.2018.
 */

public interface StringErrorResource<E> {
    E getEmptyError();

    E getLengthError();

    E getEmptyErrorName();

    E getLengthErrorName();

    E getPatternErrorName();

    E getEmptyErrorAge();

    E getLengthErrorAge();

    E getEmptyErrorUserName();

    E getLengthErrorUserName();

    E getPatternErrorUserName();

    E getEmptyPhone();

    E getErrorPassword();

    E getLengthErrorPassword();

    E getPatternErrorReferral();

    E getEmptyErrorEmail();

    E getPatternErrorEmail();

    E getErrorConfirm();
}
