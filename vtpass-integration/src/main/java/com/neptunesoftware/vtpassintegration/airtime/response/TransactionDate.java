package com.neptunesoftware.vtpassintegration.airtime.response;

import java.util.Date;

public record TransactionDate(
        Date date,
        Integer timezone_type,
        String timezone
) {
}
