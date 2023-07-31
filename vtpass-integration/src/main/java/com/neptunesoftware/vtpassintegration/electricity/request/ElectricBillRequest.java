package com.neptunesoftware.vtpassintegration.electricity.request;

import lombok.Data;

@Data
public class ElectricBillRequest {
    private String request_id;
    private String serviceID;
    private String billersCode;
    private String variation_code;
    private String amount;
    private String phone;
}
