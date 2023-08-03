package com.neptunesoftware.vtpassintegration.education.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class JAMBPINVendingRequest {
    private String request_id;
    @NonNull
    private String serviceID;
    @NonNull
    private String variation_code;
    private int quantity;
    @NonNull
    private String phone;

}

