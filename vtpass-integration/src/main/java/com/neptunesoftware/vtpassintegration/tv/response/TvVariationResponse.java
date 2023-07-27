package com.neptunesoftware.vtpassintegration.tv.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class TvVariationResponse {
    private String serviceName;
    private String convenienceFee;
    private List<TvVariations> variations;
}
