package com.neptunesoftware.vtpassintegration.transaction.response;

public record Transactions(
        String status ,
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
