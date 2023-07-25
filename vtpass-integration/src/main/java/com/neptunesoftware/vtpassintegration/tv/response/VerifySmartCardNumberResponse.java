package com.neptunesoftware.vtpassintegration.tv.response;

import lombok.Data;

@Data
public class VerifySmartCardNumberResponse {
    private String code;
    private SmartCardContent content;
}
