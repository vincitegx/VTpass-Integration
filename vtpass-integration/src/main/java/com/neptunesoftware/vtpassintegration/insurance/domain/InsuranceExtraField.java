package com.neptunesoftware.vtpassintegration.insurance.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InsuranceExtraField {

    @JsonProperty("optionName")
    private String optionName;

    @JsonProperty("optionType")
    private String optionType;

    @JsonProperty("optionLabel")
    private String optionLabel;

    @JsonProperty("optionRule")
    private String optionRule;
}
