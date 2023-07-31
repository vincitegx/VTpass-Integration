package com.neptunesoftware.vtpassintegration.transaction.response;

public record TransactionResponse(
        String code,
        String status,
        String requestId
) {
}
