package com.neptunesoftware.vtpassintegration.tv.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TvVariationResponse {
    private String serviceName;
    private String convenienceFee;
    private String variationName;
    private String variationAmount;
    private String variationCode;
}
