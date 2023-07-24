package com.neptunesoftware.vtpassintegration.data.request;

import lombok.NonNull;

public record DataSubscriptionRequest(
        @NonNull
        String requestId,
        @NonNull
        String serviceId,
        @NonNull
        String billersCode,
        @NonNull
        String variationCode,
        Integer amount,
        @NonNull
        Integer phone
) {
}
