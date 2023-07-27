package com.neptunesoftware.vtpassintegration.data.request;

import lombok.NonNull;

public record SmileVerificationRequest(
        @NonNull
        String billersCode,
        @NonNull
        String serviceID
) {
}
