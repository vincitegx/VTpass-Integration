package com.neptunesoftware.vtpassintegration.electricity.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElectricBillResponse {
    private String code;
    private String requestId;
    private String transactionId;
    private String serviceId;
    private String status;
    private String description;
    private String uniqueElement;
    private String transactionAmount;
    private String transactionType;
    private String transactionPlatform;
    private String productName;
    private String taxAmount;
    private String phoneNumber;
    private String method;
}