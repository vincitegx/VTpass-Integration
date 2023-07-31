package com.neptunesoftware.vtpassintegration.tv.request;

import lombok.Data;

@Data
public class TvSubscriptionRequest {
    private String request_id;
    private String serviceID;
    private int amount;
    private String billersCode;
    private String phone;
    private String variation_code;
    private String quantity;
    private String subscription_type;
}
