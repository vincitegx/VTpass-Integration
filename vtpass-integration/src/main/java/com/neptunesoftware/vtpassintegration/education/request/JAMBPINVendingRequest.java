package com.neptunesoftware.vtpassintegration.education.request;

import lombok.Data;

@Data
public class JAMBPINVendingRequest {
    private String request_id;
    private String serviceID;
    private String variation_code;
    private int quantity;
    private String phone;

}

