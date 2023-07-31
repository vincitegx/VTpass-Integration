package com.neptunesoftware.vtpassintegration.insurance.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HomeCoverPurchaseRequest {

    private String request_id;
    private String serviceID;
    private String billersCode;
    private String variation_code;
    private BigDecimal amount;
    private String phone;
    private String full_name;
    private String address;
    private String type_building;
    private String business_occupation;
    private String date_of_birth;
}
