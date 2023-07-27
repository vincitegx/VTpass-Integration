package com.neptunesoftware.vtpassintegration.tv.response;

import lombok.Data;

@Data
public class SmartCardVerificationApiResponse {
    private String code;
    private SmartCardContent content;
}
