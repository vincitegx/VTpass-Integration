package com.neptunesoftware.vtpassintegration.tv.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TvSubscriptionResponse {
    private  String status;
    private  String request_id;
}
