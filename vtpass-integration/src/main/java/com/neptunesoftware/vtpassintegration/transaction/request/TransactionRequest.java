package com.neptunesoftware.vtpassintegration.transaction.request;

public record TransactionRequest(
        String request_id,
        String service_id,
        String transaction_id,
        String response_code,
        String transaction_date
) {
}
