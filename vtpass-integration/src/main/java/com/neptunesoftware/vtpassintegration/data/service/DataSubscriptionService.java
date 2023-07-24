package com.neptunesoftware.vtpassintegration.data.service;

import com.neptunesoftware.vtpassintegration.data.repository.DataSubscriptionRepository;
import com.neptunesoftware.vtpassintegration.data.request.DataSubscriptionRequest;
import com.neptunesoftware.vtpassintegration.data.response.DataSubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataSubscriptionService {

    private final DataSubscriptionRepository dataSubscriptionRepository;

    public DataSubscriptionResponse subscribeForData(DataSubscriptionRequest dataSubscriptionRequest){
        return null;
    }
}
