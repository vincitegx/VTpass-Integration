package com.neptunesoftware.vtpassintegration.education.domain;


import java.util.Date;

public record TransactionDate (
        Date date,
        Integer timezoneType,
        String timezone
){
}
