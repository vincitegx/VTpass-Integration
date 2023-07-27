package com.neptunesoftware.vtpassintegration.education.domain;

import lombok.Data;

@Data
public class Transaction {

    private String status;
    private String product_name;
    private String unique_element;
    private int unit_price;
    private int quantity;
    private String service_verification;
    private String channel;
    private Integer commission;
    private Integer total_amount;
    private String discount;
    private String type;
    private String email;
    private String phone;
    private String name;
    private Integer convinience_fee;
    private Integer amount;
    private String platform;
    private String method;
    private String transactionId;
}
