package com.neptunesoftware.vtpassintegration.tv.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionDate {
    private String date;
    @JsonProperty("timezone_type")
    private int timezoneType;
    private String timezone;
}
