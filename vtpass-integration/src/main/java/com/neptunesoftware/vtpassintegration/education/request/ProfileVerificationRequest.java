package com.neptunesoftware.vtpassintegration.education.request;

import lombok.Data;

@Data
public class ProfileVerificationRequest {
    private String billersCode;
    private String serviceID;
    private String type;


}

