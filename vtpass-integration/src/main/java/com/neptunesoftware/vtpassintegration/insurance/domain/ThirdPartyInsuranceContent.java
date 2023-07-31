package com.neptunesoftware.vtpassintegration.insurance.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ThirdPartyInsuranceContent {

    @JsonProperty("transactions")
    private InsuranceTransaction transactions;
}
