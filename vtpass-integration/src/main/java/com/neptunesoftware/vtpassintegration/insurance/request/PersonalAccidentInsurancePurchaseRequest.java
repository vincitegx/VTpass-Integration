package com.neptunesoftware.vtpassintegration.insurance.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class PersonalAccidentInsurancePurchaseRequest {

    private String request_id;
    @NonNull
    private String serviceID;
    @NonNull
    private String billersCode;
    @NonNull
    private String variation_code;
    private Integer amount;
    @NonNull
    private String phone;
    @NonNull
    private String full_name;
    @NonNull
    private String address;
    @NonNull
    private String dob;
    @NonNull
    private String next_kin_name;
    @NonNull
    private String next_kin_phone;
    @NonNull
    private String business_occupation;

}
