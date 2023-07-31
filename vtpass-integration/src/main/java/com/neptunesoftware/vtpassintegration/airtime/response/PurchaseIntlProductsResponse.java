package com.neptunesoftware.vtpassintegration.airtime.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

public record PurchaseIntlProductsResponse(
String code,
Content content,
String response_description,
String requestId,
String amount,
TransactionDate transaction_date,
String purchased_code
) {


public record Content(Transactions transactions){ }



 public record Transactions(
String status,
String product_name,
String unique_element,
@JsonProperty("unit_price")
String unit_price,
String quantity,
String  service_verification,
String  channel,
Integer commission,
Integer total_amount,
String discount,
String type,
String email,
String phone,
String name,
Integer convinience_fee,
String amount,
String platform,
String method,
String transactionId
 ){}

}
