package com.neptunesoftware.vtpassintegration.tv.response;

import com.neptunesoftware.vtpassintegration.tv.domain.TvVariations;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TvVariationResponseApi {
    private String serviceName;
    private String convenienceFee;
    private List<TvVariations> variations;
}
