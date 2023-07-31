package com.neptunesoftware.vtpassintegration.electricity.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostpaidResponse implements ElectricBillGenericResponse<ElectricPostpaidResponse> {
    private String status;
    private ElectricPostpaidResponse postpaidResponse;

    @Override
    public String status() {
        return status;
    }

    @Override
    public ElectricPostpaidResponse getData() {
        return postpaidResponse;
    }
}
