package com.neptunesoftware.vtpassintegration.transaction.request;

public record CallBackRequest(
        String type,
        Data data,
        String response_description,
        Integer amount,
        TransactionDate transaction_date,
        String requestId,
        String purchase_code
) {
    public record Data(
            String code,
            Content content
    ) {
    }

    public record Content(
            Transaction transactions
    ) {
    }

    public record Transaction(
            String status,
            String product_name,
            String unique_element,
            Integer unit_price,
            Integer quantity,
            String service_verification,
            String channel,
            Integer commission,
            Double total_amount,
            Double discount,
            String type,
            String phone,
            String name,
            Integer convinience_fee,
            Integer amount,
            String platform,
            String method,
            String transactionId
    ) {
    }

    public record TransactionDate(
            String date,
            Integer timezone_type,
            String timezone
    ) {
    }
}