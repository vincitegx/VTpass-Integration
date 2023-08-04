package com.neptunesoftware.vtpassintegration.electricity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neptunesoftware.vtpassintegration.electricity.domain.ElectricPayContent;
import com.neptunesoftware.vtpassintegration.electricity.domain.ElectricTransactionDate;
import lombok.Data;

@Data
public class ElectricityPaymentResponse {
    private String code;
    private ElectricPayContent content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String response_description;
    private String requestId;
    private String amount;
    private ElectricTransactionDate transaction_date;
    private String purchased_code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customerName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customerAddress;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;
    private double tokenAmount;
    private String exchangeReference;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String resetToken;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String configureToken;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String units;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fixChargeAmount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tariff;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String taxAmount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String utilityName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String balance;
}
