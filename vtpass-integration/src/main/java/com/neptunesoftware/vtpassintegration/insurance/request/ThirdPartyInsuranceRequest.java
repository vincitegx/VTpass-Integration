package com.neptunesoftware.vtpassintegration.insurance.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ThirdPartyInsuranceRequest {

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

    @JsonProperty("Insured_Name")
    private String insuredName;

    @JsonProperty("Engine_Number")
    private String engineNumber;

    @JsonProperty("Chasis_Number")
    private String chasisNumber;

    @JsonProperty("Plate_Number")
    private String plateNumber;

    @JsonProperty("Vehicle_Make")
    private String vehicleMake;

    @JsonProperty("Vehicle_Color")
    private String vehicleColor;

    @JsonProperty("Vehicle_Model")
    private String vehicleModel;

    @JsonProperty("Year_of_Make")
    private String yearOfMake;

    @JsonProperty("Contact_Address")
    private String contactAddress;
}
