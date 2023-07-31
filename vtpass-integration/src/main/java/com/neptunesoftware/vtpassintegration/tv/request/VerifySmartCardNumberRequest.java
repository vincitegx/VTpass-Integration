package com.neptunesoftware.vtpassintegration.tv.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifySmartCardNumberRequest {
   public  String billersCode;
   public String serviceID;

}
