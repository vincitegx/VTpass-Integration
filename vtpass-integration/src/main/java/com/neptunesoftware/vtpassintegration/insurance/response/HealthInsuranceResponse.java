package com.neptunesoftware.vtpassintegration.insurance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neptunesoftware.vtpassintegration.education.domain.Content;
import com.neptunesoftware.vtpassintegration.insurance.domain.InsuranceContent;
import lombok.Data;

@Data
public class HealthInsuranceResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("content")
    private InsuranceContent content;

    @JsonProperty("response_description")
    private String responseDescription;

    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("amount")
    private String amount;

}
