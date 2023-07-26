package com.neptunesoftware.vtpassintegration.data.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class DataSubscriptionRequest {
        private String request_id;
        @NonNull
        private String serviceID;
        @NonNull
        private String billersCode;
        @NonNull
        private String variation_code;
        Integer amount;
        @NonNull
        private String phone;
}
