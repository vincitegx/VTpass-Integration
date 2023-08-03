package com.neptunesoftware.vtpassintegration.education.request;

import lombok.Data;

@Data
public class JAMBProductPurchaseRequest {

    private String request_id;
    private String serviceID;
    private String variation_code;
    private String billersCode;
    private String phone;
}
