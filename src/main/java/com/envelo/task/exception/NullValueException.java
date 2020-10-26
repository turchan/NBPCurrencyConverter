package com.envelo.task.exception;

import lombok.Getter;

@Getter
public class NullValueException extends RuntimeException {

    private String rateCode;
    private String rateCodeTarget;

    public NullValueException(String message, String rateCode) {
        super(String.format("The values of '%s' is not allow for a conversion", rateCode));
        this.rateCode = rateCode;
    }

    public NullValueException(String message, String rateCode, String rateCodeTarget) {
        super(String.format("The values of '%s' or '%s' is not allow for a conversion", rateCode, rateCodeTarget));
        this.rateCode = rateCode;
        this.rateCodeTarget = rateCodeTarget;
    }
}
