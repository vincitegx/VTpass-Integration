package com.neptunesoftware.vtpassintegration.education.request;

import lombok.Data;

@Data
public class JAMBProfileVerificationRequest {

    private String billersCode;
    private String serviceID;
    private String type;
}
