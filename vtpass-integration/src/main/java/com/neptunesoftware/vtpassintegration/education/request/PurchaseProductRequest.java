package com.neptunesoftware.vtpassintegration.education.request;


public record PurchaseProductRequest(
        String requestId,
        String serviceID,
        String variationCode,
        Double amount,
        int quantity,
        String phone
) {}

