package com.neptunesoftware.vtpassintegration.education.request;

import lombok.Data;



public record PurchaseProductRequest(
        String requestId, String serviceID, String variationCode, Double amount, int quantity, String phone
) {}











//@Data
//public class PurchaseProductRequest {
//
//    private String requestId;
//    private String serviceID;
//    private String variationCode;
//    private Double amount;
//    private int quantity;
//    private String phone;
//
//}
