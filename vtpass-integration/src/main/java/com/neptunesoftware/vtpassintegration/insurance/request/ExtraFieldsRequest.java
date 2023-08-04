package com.neptunesoftware.vtpassintegration.insurance.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class ExtraFieldsRequest {

    @NonNull
    private String serviceID;
}
