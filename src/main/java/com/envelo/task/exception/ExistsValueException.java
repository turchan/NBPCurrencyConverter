package com.envelo.task.exception;

import lombok.Getter;

@Getter
public class ExistsValueException extends RuntimeException {

    private String currencyCode;

    public ExistsValueException(String message, String currencyCode) {
        super(String.format("The currency with code '%s' is already exist in allow list", currencyCode));
        this.currencyCode = currencyCode;
    }
}
