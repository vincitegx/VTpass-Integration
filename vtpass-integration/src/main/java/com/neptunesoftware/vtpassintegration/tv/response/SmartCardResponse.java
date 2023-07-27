package com.neptunesoftware.vtpassintegration.tv.response;

import lombok.Data;

@Data
public class SmartCardResponse {
    private String status;
    private String cardHolderName;
    private String smartCardNumber;
    private String amountDueRenewal;
    private String currentBouquet;
    private String currentBouquetCode;
    private String dueDate;

}
