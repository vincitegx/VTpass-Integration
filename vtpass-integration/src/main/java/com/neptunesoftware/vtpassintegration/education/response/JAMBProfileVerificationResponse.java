package com.neptunesoftware.vtpassintegration.education.response;

import com.neptunesoftware.vtpassintegration.education.domain.Content;
import lombok.Data;

@Data
public class JAMBProfileVerificationResponse {

    private String code;
    private Content content;
}
