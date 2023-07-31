package com.neptunesoftware.vtpassintegration.insurance.response;

import com.neptunesoftware.vtpassintegration.insurance.domain.InsuranceContent;
import lombok.Data;

@Data
public class PersonalAccidentInsuranceResponse {

    private String code;
    private InsuranceContent content;
}
