package com.neptunesoftware.vtpassintegration.education.response;

public record JAMBProfileVerificationResponse (
        String code,
        Content content
){
    public record Content(
            String Customer_Name
    ) {
    }
}
