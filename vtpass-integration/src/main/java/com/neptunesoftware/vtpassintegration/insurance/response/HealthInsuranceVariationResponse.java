package com.neptunesoftware.vtpassintegration.insurance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neptunesoftware.vtpassintegration.education.domain.Content;
import com.neptunesoftware.vtpassintegration.insurance.domain.InsuranceContent;

public class HealthInsuranceVariationResponse {

    @JsonProperty("response_description")
    private String responseDescription;

    @JsonProperty("content")
    private InsuranceContent content;
}
