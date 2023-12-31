package com.neptunesoftware.vtpassintegration.airtime.request;
import lombok.Data;
import lombok.NonNull;

@Data
public class PurchaseIntlProductsRequest {
    String request_id;
    @NonNull
    String serviceID;

    @NonNull
    String billersCode;
    @NonNull
    String variation_code;
    @NonNull
    String amount;
    String phone;
    @NonNull
    String operator_id;
    @NonNull
    String country_code;
    @NonNull
    String product_type_id;

    @NonNull
    String email;
}
