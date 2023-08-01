package com.neptunesoftware.vtpassintegration.electricity.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ElectricBillPaymentException extends RuntimeException {
    private String code;

    public ElectricBillPaymentException(String message, String code) {
        super(message);
        this.code = code;
    }
}
