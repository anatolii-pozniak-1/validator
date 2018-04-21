package com.pro.anat.validation;

public interface Validation<V, E> {
    boolean isValid();

    void setValue(V actual);

    E getError();

    V getValue();
}
