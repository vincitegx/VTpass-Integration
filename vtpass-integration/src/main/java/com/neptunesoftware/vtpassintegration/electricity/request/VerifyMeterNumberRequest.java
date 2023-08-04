package com.neptunesoftware.vtpassintegration.electricity.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyMeterNumberRequest {
   private String billersCode;
   private String serviceID;
   private String type;
}
