package com.neptunesoftware.vtpassintegration.tv.request;

import lombok.Data;

@Data
public class VerifySmartCardNumberRequest {
   public  String billersCode;
   public String serviceID;

}
