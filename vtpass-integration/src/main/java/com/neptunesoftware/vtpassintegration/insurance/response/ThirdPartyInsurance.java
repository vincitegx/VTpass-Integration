package com.neptunesoftware.vtpassintegration.insurance.response;

public record ThirdPartyInsurance(
        String code,
        Content content,
        String response_description,
        String requestId,
        String amount,
        TransactionDate transaction_date,
        String purchased_code,
        String certUrl
) {
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
            Integer total_amount,
            Integer discount,
            String type,
            String email,
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
