package com.neptunesoftware.vtpassintegration.electricity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElectricBillResponseApi {
private ElectricPrepaidResponse electricPrepaidResponse;
private ElectricPostpaidResponse electricPostpaidResponse;
}
