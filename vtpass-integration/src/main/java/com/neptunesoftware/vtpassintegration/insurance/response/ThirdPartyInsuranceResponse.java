package com.neptunesoftware.vtpassintegration.insurance.response;

import com.neptunesoftware.vtpassintegration.insurance.domain.InsuranceContent;
import com.neptunesoftware.vtpassintegration.insurance.domain.TransactionDate;
import lombok.Data;

@Data
public class ThirdPartyInsuranceResponse {
    private String code;
    private InsuranceContent content;
    private String response_description;
    private String requestId;
    private Double amount;
    private TransactionDate transaction_date;
    private String purchased_code;
    private String certUrl;
}
