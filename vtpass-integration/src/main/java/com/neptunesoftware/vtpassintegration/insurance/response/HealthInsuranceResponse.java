package com.neptunesoftware.vtpassintegration.insurance.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neptunesoftware.vtpassintegration.education.domain.Content;
import com.neptunesoftware.vtpassintegration.insurance.domain.InsuranceContent;
import lombok.Data;

@Data
public class HealthInsuranceResponse {


    private String code;
    private InsuranceContent content;
    private String responseDescription;
    private String requestId;
    private String amount;

}
