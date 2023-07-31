package com.neptunesoftware.vtpassintegration.insurance.domain;

import lombok.Data;

import java.util.Map;

@Data
public class HomeCoverOption {

    private String optionName;
    private InsuranceContent content;
    private String optionType;
    private String optionLabel;
    private String optionRule;
    private Map<String, String> options;
}
