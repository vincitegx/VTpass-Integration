package com.neptunesoftware.vtpassintegration.education.domain;


public record TransactionDate (
        String date,
        Integer timezoneType,
        String timezone
){
}
