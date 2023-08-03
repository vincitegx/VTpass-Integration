package com.neptunesoftware.vtpassintegration.airtime.response;

public record TransactionDate(
        String date,
        Integer timezone_type,
        String timezone
) {
}
