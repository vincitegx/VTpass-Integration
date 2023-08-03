package com.neptunesoftware.vtpassintegration.tv.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neptunesoftware.vtpassintegration.tv.domain.TvVariationContent;
import lombok.Data;

@Data
public class TvVariationResponseApi {
    @JsonIgnore(value = false)
    private String response_description;
    @JsonIgnore(value = false)
    private TvVariationContent content;
    @JsonIgnore(value = false)
    private String code;
}
