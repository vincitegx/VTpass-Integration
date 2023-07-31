package com.neptunesoftware.vtpassintegration.insurance.response;

import com.neptunesoftware.vtpassintegration.insurance.domain.InsuranceContent;
import lombok.Data;

import java.util.Map;

@Data
public class HomeCoverOptionResponse {
    private String response_description;
    private InsuranceContent content;
    private String ServiceName;
    private String serviceID;
    private String optionName;
    private String optionType;
    private String optionLabel;
    private String optionRule;
    private Map<String, String> options;
}
