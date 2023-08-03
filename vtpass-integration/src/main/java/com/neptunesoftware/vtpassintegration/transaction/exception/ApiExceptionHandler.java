package com.neptunesoftware.vtpassintegration.transaction.exception;

import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<TransactionResponse> transactionException(TransactionException exception) {
        return createApiResponse(HttpStatus.BAD_REQUEST, exception);
    }

    private ResponseEntity<TransactionResponse> createApiResponse(HttpStatus httpStatus, TransactionException e) {
        return new ResponseEntity<>(
                new TransactionResponse(e.getCode(), e.getMessage(), e.getRequestId()),
                httpStatus);
    }
}
