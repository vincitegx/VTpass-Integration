package com.neptunesoftware.vtpassintegration.tv.request;

import lombok.Data;

@Data
public class TvRequest {
    private String request_id;
    private String serviceID;
    private String billersCode;
    private String variation_code;
    private String amount;
    private String phone;
    private String subscription_type;
    private String quantity;
}
