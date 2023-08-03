package com.neptunesoftware.vtpassintegration.education.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class JAMBProfileVerificationRequest {

    @NonNull
    private String billersCode;
    @NonNull
    private String serviceID;
    @NonNull
    private String type;

}
