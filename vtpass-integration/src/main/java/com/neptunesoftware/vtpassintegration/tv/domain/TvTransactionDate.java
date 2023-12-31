package com.neptunesoftware.vtpassintegration.tv.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TvTransactionDate {
    private String date;
    @JsonProperty("timezone_type")
    private int timezoneType;
    private String timezone;
}
