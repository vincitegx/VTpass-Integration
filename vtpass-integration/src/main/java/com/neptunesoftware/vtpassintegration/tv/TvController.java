package com.neptunesoftware.vtpassintegration.tv;

import com.neptunesoftware.vtpassintegration.tv.request.TvSubscriptionStatusRequest;
import com.neptunesoftware.vtpassintegration.tv.request.VerifySmartCardNumberRequest;
import com.neptunesoftware.vtpassintegration.tv.response.TvSubscriptionStatusResponse;
import com.neptunesoftware.vtpassintegration.tv.response.VerifySmartCardNumberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v3/accounts")
@Log4j2
//@Tag(name = "tvSubscription")
public class TvController {
    private final TvServices tvServices;
    @PostMapping("/verify-smart-card-number")
    public ResponseEntity<VerifySmartCardNumberResponse> verifySmartCardNumber(@RequestBody VerifySmartCardNumberRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tvServices.verifySmartCardNumber(request));
    }

    @PostMapping("/tv-subscription-status")
    public ResponseEntity<TvSubscriptionStatusResponse> tvSubscriptionStatus(@RequestBody TvSubscriptionStatusRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(tvServices.tvSubscriptionStatus(request));
    }
}
