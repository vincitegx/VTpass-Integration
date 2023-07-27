package com.neptunesoftware.vtpassintegration.transaction.response;
public record TransactionQueryResponse(String code,
                                       Content content,
                                       String response_description,
                                       String requestId,
                                       String amount,
                                       TransactionDate transaction_date,
                                       String purchased_code
) {
    public record Content(
            Transaction transactions
    ) {
    }
    public record Transaction(
            String status,
            String product_name,
            String unique_element,
            String unit_price,
            Integer quantity,
            String service_verification,
            String channel,
            Integer commission,
            String total_amount,
            Integer discount,
            String type,
            String email,
            String phone,
            String name,
            Integer convenience_fee,
            String amount,
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
