package com.neptunesoftware.vtpassintegration.tv.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TvVariationFromApi {
    @JsonProperty("response_description")
    private String response_description;
    private TvVariationContent content;
}
