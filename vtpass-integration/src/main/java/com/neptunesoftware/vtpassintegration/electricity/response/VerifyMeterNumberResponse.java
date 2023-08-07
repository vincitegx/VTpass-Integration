package com.neptunesoftware.vtpassintegration.electricity.response;

import com.neptunesoftware.vtpassintegration.electricity.domain.ElectricityVerificationContent;
import lombok.Data;

@Data
public class VerifyMeterNumberResponse {
    private String code;
    private ElectricityVerificationContent content;
}