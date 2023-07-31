package com.neptunesoftware.vtpassintegration.insurance.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HealthInsuranceRequest {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("serviceID")
    private String serviceID;

    @JsonProperty("billersCode")
    private String billersCode;

    @JsonProperty("variation_code")
    private String variationCode;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("selected_hospital")
    private String selectedHospital;

    @JsonProperty("Passport_Photo")
    private String passportPhoto;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @JsonProperty("extra_info")
    private String extraInfo;
}
