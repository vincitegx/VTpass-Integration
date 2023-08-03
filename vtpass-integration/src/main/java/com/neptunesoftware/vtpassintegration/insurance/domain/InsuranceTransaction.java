package com.neptunesoftware.vtpassintegration.insurance.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InsuranceTransaction {

    private String status;

    private String product_name;

    private String unique_element;

    private Integer unit_price;

    private Integer quantity;

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
