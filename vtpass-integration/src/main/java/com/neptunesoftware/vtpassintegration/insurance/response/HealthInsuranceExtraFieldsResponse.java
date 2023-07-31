package com.neptunesoftware.vtpassintegration.insurance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neptunesoftware.vtpassintegration.insurance.domain.HealthInsuranceExtraField;

import java.util.List;

public class HealthInsuranceExtraFieldsResponse {

    @JsonProperty("response_description")
    private String responseDescription;

    @JsonProperty("ServiceName")
    private String serviceName;

    @JsonProperty("serviceID")
    private String serviceID;

    @JsonProperty("content")
    private List<HealthInsuranceExtraField> content;
}
