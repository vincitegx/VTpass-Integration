package com.neptunesoftware.vtpassintegration.tv.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TvVariationContent {
    @JsonProperty("ServiceName")
    private String serviceName;
    private String serviceID;
    private String convinience_fee;
    private List<TvVariations> varations;
    private String errors; // Add the "errors" field

}
