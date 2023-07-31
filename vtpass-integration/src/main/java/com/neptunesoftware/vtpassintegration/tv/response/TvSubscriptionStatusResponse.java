package com.neptunesoftware.vtpassintegration.tv.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TvSubscriptionStatusResponse {
    private  String status;
    private  String productName;
}
