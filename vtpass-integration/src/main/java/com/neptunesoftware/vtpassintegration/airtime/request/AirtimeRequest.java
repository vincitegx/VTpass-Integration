package com.neptunesoftware.vtpassintegration.airtime.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class AirtimeRequest {

    @NonNull
    private String request_id ;
    @NonNull
    private String serviceID ;
    @NonNull
    private Integer amount ;
    @NonNull
    private Integer phone ;
}
