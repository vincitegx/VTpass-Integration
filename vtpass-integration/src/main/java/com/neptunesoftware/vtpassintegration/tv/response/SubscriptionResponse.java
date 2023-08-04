package com.neptunesoftware.vtpassintegration.tv.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neptunesoftware.vtpassintegration.tv.domain.TvContent;
import com.neptunesoftware.vtpassintegration.tv.domain.TvTransactionDate;
import lombok.Data;

@Data
public class SubscriptionResponse {
    private String code;
    private TvContent content;
    @JsonProperty("response_description")
    private String responseDescription;
    private String requestId;
    private String amount;
    @JsonProperty("transaction_date")
    private TvTransactionDate transactionDate;
    @JsonProperty("purchased_code")
    private String purchasedCode;
}
