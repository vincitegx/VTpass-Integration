package com.neptunesoftware.vtpassintegration.electricity.response;

import com.neptunesoftware.vtpassintegration.electricity.domain.ElectricBillContent;
import com.neptunesoftware.vtpassintegration.electricity.domain.ElectricTransactionDate;
import lombok.Data;

@Data
public class ElectricPrepaidResponse {
    private String code;
    private ElectricBillContent content;
    private String response_description;
    private String requestId;
    private String amount;
    private ElectricTransactionDate transaction_date;
    private String purchased_code;
    private String customerName;
    private String customerAddress;
    private String token;
    private double tokenAmount;
    private String exchangeReference;
    private String resetToken;
    private String configureToken;
    private String units;
    private String fixChargeAmount;
    private String tariff;
    private String taxAmount;
}
