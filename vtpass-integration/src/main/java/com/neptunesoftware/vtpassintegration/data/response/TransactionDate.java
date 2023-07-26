package com.neptunesoftware.vtpassintegration.data.response;

import java.util.Date;

public record TransactionDate(
        String date,
        Integer timezone_type,
        String timezone
) {
}
