package com.neptunesoftware.vtpassintegration.education.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class JAMBProductPurchaseRequest {

    private String request_id;
    @NonNull
    private String serviceID;
    @NonNull
    private String variation_code;
    @NonNull
    private String billersCode;
    @NonNull
    private String phone;
}
