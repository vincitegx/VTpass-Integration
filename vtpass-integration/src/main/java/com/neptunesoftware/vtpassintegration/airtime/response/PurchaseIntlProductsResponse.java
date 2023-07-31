package com.neptunesoftware.vtpassintegration.airtime.response;

public record PurchaseIntlProductsResponse(
       String code,
      Content  content,
       String response_description,
      String  requestId,
      Integer  amount,
       TransactionDate transaction_date,
       String purchased_code
) {
  public record Content (
          Transactions transactions
  ){}


 public record Transactions(
String status,
String product_name,
String unique_element,
Integer unit_price,
Integer quantity,
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
Integer amount,
String platform,
String method,
String transactionId
 ){}

}
