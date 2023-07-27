package com.neptunesoftware.vtpassintegration.education.response;

import lombok.Data;

@Data
public class ProfileVerificationResponse {
    private String code;
    private Content content;
    // Add any additional fields as needed



    public static class Content {
        private String Customer_Name;
        // Add any additional fields as needed

        // Getters and setters
    }
}

