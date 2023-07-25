package com.neptunesoftware.vtpassintegration.data.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class DataSubscriptionRequest {
        @NonNull
        private String requestId;
        @NonNull
        private String serviceId;
        @NonNull
        private String billersCode;
        @NonNull
        private String variationCode;
        Integer amount;
        @NonNull
        private Integer phone;
}
