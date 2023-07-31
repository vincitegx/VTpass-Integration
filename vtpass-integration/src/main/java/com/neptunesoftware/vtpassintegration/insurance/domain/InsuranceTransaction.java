package com.neptunesoftware.vtpassintegration.insurance.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InsuranceTransaction {

    @JsonProperty("status")
    private String status;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("unique_element")
    private String uniqueElement;

    @JsonProperty("unit_price")
    private int unitPrice;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("service_verification")
    private String serviceVerification;

    @JsonProperty("channel")
    private String channel;

    @JsonProperty("commission")
    private Integer commission;

    @JsonProperty("total_amount")
    private int totalAmount;

    @JsonProperty("discount")
    private String discount;

    @JsonProperty("type")
    private String type;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("name")
    private String name;

    @JsonProperty("convinience_fee")
    private int convenienceFee;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("method")
    private String method;

    @JsonProperty("transactionId")
    private String transactionId;
}
