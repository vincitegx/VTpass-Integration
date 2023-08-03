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

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<TransactionResponse> runtimeException(RuntimeException exception) {
        return createApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    private ResponseEntity<TransactionResponse> createApiResponse(HttpStatus httpStatus, TransactionException e) {
        return new ResponseEntity<>(
                new TransactionResponse(e.getCode(), e.getMessage(), e.getRequestId()),
                httpStatus);
    }
    private ResponseEntity<TransactionResponse> createApiResponse(HttpStatus httpStatus, RuntimeException e) {
        return new ResponseEntity<>(
                new TransactionResponse(null, e.getLocalizedMessage(), null),
                httpStatus);
    }
}
