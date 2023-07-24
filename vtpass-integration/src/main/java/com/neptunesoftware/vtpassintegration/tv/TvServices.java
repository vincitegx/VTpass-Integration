package com.neptunesoftware.vtpassintegration.tv;

import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionStatusRequest;
import com.neptunesoftware.vtpassintegration.tv.request.VerifySmartCardNumberRequest;
import com.neptunesoftware.vtpassintegration.tv.response.TvSubscriptionStatusResponse;
import com.neptunesoftware.vtpassintegration.tv.response.VerifySmartCardNumberResponse;
import org.springframework.stereotype.Service;

@Service
public class TvServices {
    public VerifySmartCardNumberResponse verifySmartCardNumber(VerifySmartCardNumberRequest request) {
        return null;
    }
    
    public TvSubscriptionStatusResponse tvSubscriptionStatus(TvSubscriptionStatusRequest request) {
        return null;
    }
}
