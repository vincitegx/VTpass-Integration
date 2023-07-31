package com.neptunesoftware.vtpassintegration.transaction.exception;

import lombok.Data;

@Data
public class TransactionException extends RuntimeException{
    private String message;
    private String code;
    private String requestId;
    public TransactionException(String message) {
        super(message);
    }
    public TransactionException(String message, String code, String requestId){
        this.message = message;
        this.code = code;
        this.requestId = requestId;
    }
}
