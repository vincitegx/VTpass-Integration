package com.neptunesoftware.vtpassintegration.insurance.request;

import lombok.Data;

@Data
public class PersonalAccidentInsurancePurchaseRequest {

    private String request_id;
    private String serviceID;
    private String billersCode;
    private String variation_code;
    private Double amount;
    private String phone;
    private String full_name;
    private String address;
    private String dob;
    private String next_kin_name;
    private String next_kin_phone;
    private String business_occupation;

}
