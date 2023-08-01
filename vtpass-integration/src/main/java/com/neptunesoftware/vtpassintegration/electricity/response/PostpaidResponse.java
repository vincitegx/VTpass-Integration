package com.neptunesoftware.vtpassintegration.electricity.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostpaidResponse  {
    private String status;
    private ElectricPostpaidResponse postpaidResponse;
}
