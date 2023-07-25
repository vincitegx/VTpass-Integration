package com.neptunesoftware.vtpassintegration.data.response;

import java.util.Date;

public record TransactionDate(
        Date date,
        Integer timezone_type,
        String timezone
) {
}
