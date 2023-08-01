package com.neptunesoftware.vtpassintegration.tv;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TvSubscriptionException extends RuntimeException{
    private String code;
    public TvSubscriptionException(String message, String code) {
        super(message);
        this.code = code;
    }
}
