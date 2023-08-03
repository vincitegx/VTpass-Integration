package com.neptunesoftware.vtpassintegration.insurance.request;

import lombok.Data;

@Data
public class HealthInsuranceRequest {
    private String request_id;
    private String serviceID;
    private String billersCode;
    private String variation_code;
    private Double amount;
    private String phone;
    private String full_name;
    private String address;
    private String selected_hospital;
    private String Passport_Photo;
    private String date_of_birth;
    private String extra_info;
}
