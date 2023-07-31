package com.neptunesoftware.vtpassintegration.electricity.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrepaidResponse implements ElectricBillGenericResponse<ElectricPrepaidResponse> {
    private String status;
    private ElectricPrepaidResponse prepaidResponse;

    @Override
    public String status() {
        return status;
    }

    @Override
    public ElectricPrepaidResponse getData() {
        return prepaidResponse;
    }
}
