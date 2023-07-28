package com.neptunesoftware.vtpassintegration.insurance.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ThirdPartyInsuranceTransaction {

    @JsonProperty("status")
    private String status;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("unique_element")
    private String uniqueElement;

    @JsonProperty("unit_price")
    private int unitPrice;

    @JsonProperty("quantity")
    private int quantity;
}
