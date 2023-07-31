package com.neptunesoftware.vtpassintegration.insurance.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InsuranceContent {

    @JsonProperty("transactions")
    private InsuranceTransaction transaction;
}
