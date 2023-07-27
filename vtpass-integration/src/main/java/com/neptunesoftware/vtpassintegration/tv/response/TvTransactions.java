package com.neptunesoftware.vtpassintegration.tv.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TvTransactions {
    private String status;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("unique_element")
    private String uniqueElement;
    @JsonProperty("unit_price")
    private int unitPrice;
    private int quantity;
    @JsonProperty("service_verification")
    private String serviceVerification;
    private String channel;
    private int commission;
    @JsonProperty("total_amount")
    private int totalAmount;
    private String discount;
    private String type;
    private String email;
    private String phone;
    private String name;
    @JsonProperty("convinience_fee")
    private int convenienceFee;
    private int amount;
    private String platform;
    private String method;
    @JsonProperty("transactionId")
    private String transactionId;
}
