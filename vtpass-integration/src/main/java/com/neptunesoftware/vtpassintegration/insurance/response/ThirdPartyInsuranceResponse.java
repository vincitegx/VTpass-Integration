package com.neptunesoftware.vtpassintegration.insurance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neptunesoftware.vtpassintegration.education.domain.Content;
import lombok.Data;

@Data
public class ThirdPartyInsuranceResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("content")
    private Content content;

    @JsonProperty("response_description")
    private String responseDescription;

    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("amount")
    private String amount;

}
