package com.neptunesoftware.vtpassintegration.insurance.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class HealthInsuranceRequest {
    private String request_id;
    @NonNull
    private String serviceID;
    @NonNull
    private String billersCode;
    @NonNull
    private String variation_code;
    private Double amount;
    @NonNull
    private String phone;
    @NonNull
    private String full_name;
    @NonNull
    private String address;
    @NonNull
    private String selected_hospital;
    @NonNull
    private String Passport_Photo;
    @NonNull
    private String date_of_birth;
    @NonNull
    private String extra_info;
}
