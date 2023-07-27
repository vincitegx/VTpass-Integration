package com.neptunesoftware.vtpassintegration.education.domain;


import java.util.Date;

public record TransactionDate (
        String date,
        Integer timezoneType,
        String timezone
){
}
