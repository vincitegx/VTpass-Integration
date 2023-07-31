package com.neptunesoftware.vtpassintegration.insurance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HealthInsuranceOptionsResponse {

    @JsonProperty("response_description")
    private String responseDescription;

    @JsonProperty("content")
    private HealthInsuranceOptionsContent content;
}
