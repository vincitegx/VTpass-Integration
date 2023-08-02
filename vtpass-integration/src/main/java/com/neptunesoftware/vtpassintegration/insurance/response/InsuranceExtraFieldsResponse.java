package com.neptunesoftware.vtpassintegration.insurance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neptunesoftware.vtpassintegration.insurance.domain.InsuranceExtraField;
import lombok.Data;

import java.util.List;

@Data
public class InsuranceExtraFieldsResponse {

    @JsonProperty("response_description")
    private String responseDescription;

    @JsonProperty("ServiceName")
    private String serviceName;

    @JsonProperty("serviceID")
    private String serviceID;

    @JsonProperty("content")
    private List<InsuranceExtraField> content;
}
