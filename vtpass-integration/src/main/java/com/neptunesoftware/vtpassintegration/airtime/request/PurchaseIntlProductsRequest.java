package com.neptunesoftware.vtpassintegration.airtime.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class PurchaseIntlProductsRequest {
    String request_id;
    @NonNull
    String serviceID;
    String billersCode;
    @NonNull
    String variation_code;
    @NonNull
    Integer amount;
    String phone;
    @NonNull
    String operator_id;
    @NonNull
    String country_code;
    @NonNull
    String product_type_id;
    String email;
}
