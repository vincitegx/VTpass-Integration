package com.neptunesoftware.vtpassintegration.electricity.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class ElectricityVerificationContent {
    @JsonProperty("Customer_Name")
    private String customerName;
    @JsonProperty("Meter_Number")
    private String meterNumber;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("CustomerArrears")
    private String customerArrears;

}
