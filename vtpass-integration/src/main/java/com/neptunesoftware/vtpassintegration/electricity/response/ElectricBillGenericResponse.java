package com.neptunesoftware.vtpassintegration.electricity.response;

public interface ElectricBillGenericResponse<T> {
    String status();
    T getData();
}
