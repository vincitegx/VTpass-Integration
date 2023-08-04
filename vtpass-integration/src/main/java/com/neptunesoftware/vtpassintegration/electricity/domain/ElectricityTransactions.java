package com.neptunesoftware.vtpassintegration.electricity.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ElectricityTransactions {
    private String status;
    private String product_name;
    private String unique_element;
    private int unit_price;
    private int quantity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String service_verification;
    private String channel;
    private int commission;
    private int total_amount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String discount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;
    private String email;
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    private int convinience_fee;
    private int amount;
    private String platform;
    private String method;
    private String transactionId;
}
