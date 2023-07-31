package com.neptunesoftware.vtpassintegration.electricity.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ElectricTransactionDate {
    private String date;
    private int timezone_type;
    private String timezone;
}
