package com.neptunesoftware.vtpassintegration.tv.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class TvSubscriptionRequest {
    private String request_id;
    @NonNull
    private String serviceID;
    private int amount;
    @NonNull
    private String billersCode;
    @NonNull
    private String phone;
    @NonNull
    private String variation_code;
    private String quantity;
    @NonNull
    private String subscription_type;
}
