package com.neptunesoftware.vtpassintegration.insurance.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class ThirdPartyInsuranceRequest {

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
    @JsonProperty("Insured_Name")
    private String insured_Name;
    @NonNull
    @JsonProperty("Engine_Number")
    private String engine_Number;
    @NonNull
    @JsonProperty("Chasis_Number")
    private String chasis_Number;

    @NonNull
    @JsonProperty("Plate_Number")
    private String plate_Number;

    @NonNull
    @JsonProperty("Vehicle_Make")
    private String vehicle_Make;

    @NonNull
    @JsonProperty("Vehicle_Color")
    private String vehicle_Color;

    @NonNull
    @JsonProperty("Vehicle_Model")
    private String vehicle_Model;

    @NonNull
    @JsonProperty("Year_of_Make")
    private String year_of_Make;

    @NonNull
    @JsonProperty("Contact_Address")
    private String contact_Address;
}
