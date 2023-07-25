package com.neptunesoftware.vtpassintegration.tv.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SmartCardContent {
    @JsonProperty("Customer_Name")
    private String customerName;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("DUE_DATE")
    private String dueDate;
    @JsonProperty("Customer_Number")
    private int customerNumber;
    @JsonProperty("Customer_Type")
    private String customerType;
    @JsonProperty("Current_Bouquet")
    private String currentBouquet;
    @JsonProperty("Current_Bouquet_Code")
    private String currentBouquetCode;
    @JsonProperty("Renewal_Amount")
    private int renewalAmount;
}
