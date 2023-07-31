package com.neptunesoftware.vtpassintegration.insurance.response;

import com.neptunesoftware.vtpassintegration.insurance.domain.InsuranceContent;
import lombok.Data;

import java.util.Date;

@Data
public class HomeCoverPurchaseResponse {

    private String code;
    private InsuranceContent content;
    private String response_description;
    private String requestId;
    private String amount;
    private Date transaction_date;
    private String purchased_code;
}
