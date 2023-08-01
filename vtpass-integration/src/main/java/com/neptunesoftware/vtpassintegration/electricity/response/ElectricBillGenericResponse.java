package com.neptunesoftware.vtpassintegration.electricity.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElectricBillGenericResponse<T> {
    private ElectricPrepaidResponse electricPrepaidResponse;
    private ElectricPostpaidResponse electricPostpaidResponse;
}
