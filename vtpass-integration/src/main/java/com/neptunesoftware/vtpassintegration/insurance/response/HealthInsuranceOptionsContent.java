package com.neptunesoftware.vtpassintegration.insurance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class HealthInsuranceOptionsContent {

    @JsonProperty("ServiceName")
    private String serviceName;

    @JsonProperty("serviceID")
    private String serviceID;

    @JsonProperty("optionName")
    private String optionName;

    @JsonProperty("optionType")
    private String optionType;

    @JsonProperty("optionLabel")
    private String optionLabel;

    @JsonProperty("optionRule")
    private String optionRule;

    @JsonProperty("options")
    private Map<String, Map<String, String>> options;

}
