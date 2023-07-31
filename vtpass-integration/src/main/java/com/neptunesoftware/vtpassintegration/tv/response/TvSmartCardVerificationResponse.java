package com.neptunesoftware.vtpassintegration.tv.response;

import com.neptunesoftware.vtpassintegration.tv.domain.SmartCardContent;
import lombok.Data;

@Data
public class TvSmartCardVerificationResponse {
    private String code;
    private SmartCardContent content;
}
