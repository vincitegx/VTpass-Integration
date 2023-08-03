package com.neptunesoftware.vtpassintegration.insurance.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ThirdPartyInsuranceRequest {

    private String request_id;
    private String serviceID;
    private String billersCode;
    private String variation_code;
    private Integer amount;
    private String phone;
    @JsonProperty("Insured_Name")
    private String insured_Name;
    @JsonProperty("Engine_Number")
    private String engine_Number;
    @JsonProperty("Chasis_Number")
    private String chasis_Number;
    @JsonProperty("Plate_Number")
    private String plate_Number;
    @JsonProperty("Vehicle_Make")
    private String vehicle_Make;
    @JsonProperty("Vehicle_Color")
    private String vehicle_Color;
    @JsonProperty("Vehicle_Model")
    private String vehicle_Model;
    @JsonProperty("Year_of_Make")
    private String year_of_Make;
    @JsonProperty("Contact_Address")
    private String contact_Address;
}
