package com.neptunesoftware.vtpassintegration.tv.response;

import lombok.Data;

import java.util.List;

@Data
public class TvVariationContent {
    private String ServiceName;
    private String serviceID;
    private String convinience_fee;
    private List<TvVariations> variations;
}
