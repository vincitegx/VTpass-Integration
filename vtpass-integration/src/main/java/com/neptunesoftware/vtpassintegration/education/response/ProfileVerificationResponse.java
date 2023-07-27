package com.neptunesoftware.vtpassintegration.education.response;

import lombok.Data;

@Data
public class ProfileVerificationResponse {
    private String code;
    private Content content;

    public static class Content {
        private String Customer_Name;

    }
}

