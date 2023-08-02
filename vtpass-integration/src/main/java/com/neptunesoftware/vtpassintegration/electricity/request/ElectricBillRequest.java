package com.neptunesoftware.vtpassintegration.electricity.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class ElectricBillRequest {
    private String request_id;
    @NonNull
    private String serviceID;
    @NonNull
    private String billersCode;
    @NonNull
    private String variation_code;
    private String amount;
    @NonNull
    private String phone;
}
