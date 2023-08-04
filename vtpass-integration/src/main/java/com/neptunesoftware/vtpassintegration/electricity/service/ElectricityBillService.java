package com.neptunesoftware.vtpassintegration.electricity.service;

import com.neptunesoftware.vtpassintegration.commons.service.RequestIdGenerator;
import com.neptunesoftware.vtpassintegration.config.Credentials;
import com.neptunesoftware.vtpassintegration.electricity.mapper.ElectricityMapper;
import com.neptunesoftware.vtpassintegration.electricity.request.ElectricBillRequest;
import com.neptunesoftware.vtpassintegration.electricity.request.VerifyMeterNumberRequest;
import com.neptunesoftware.vtpassintegration.electricity.response.ElectricityPaymentResponse;
import com.neptunesoftware.vtpassintegration.electricity.response.VerifyMeterNumberResponse;
import com.neptunesoftware.vtpassintegration.transaction.exception.TransactionException;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;
import com.neptunesoftware.vtpassintegration.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class ElectricityBillService {
    private final WebClient.Builder webClient;
    private final RequestIdGenerator requestIdGenerator;
        private final ElectricityMapper responseMapper;
    private final TransactionService transactionService;
    private final Credentials credentials;

    public TransactionResponse electricityPayment(ElectricBillRequest request) {
        TransactionResponse response;
        if (request.getVariation_code().equalsIgnoreCase("prepaid") || request.getVariation_code().equalsIgnoreCase("postpaid")) {
            VerifyMeterNumberResponse verifyMeterNumberResponse = verifyMeterNumber
                    (new VerifyMeterNumberRequest(request.getBillersCode(),
                            request.getServiceID(), request.getVariation_code()));
            if (verifyMeterNumberResponse.getContent().getMeterNumber() == null) {
                log.info("INVALID METER NUMBER: {}", request.getBillersCode());
                throw new TransactionException("INVALID METER NUMBER", "012", null);
            }
        } else {
            log.info("WRONG METER TYPE: TYPE SHOULD BE, PREPAID OR POSTPAID!!");
            throw new TransactionException("INVALID METER TYPE: TYPE SHOULD BE, PREPAID OR POSTPAID!!","012",null);
        }
            request.setRequest_id(requestIdGenerator.apply(4));
        ElectricityPaymentResponse paymentResponse = webClient.build().post()
                    .uri(credentials.getBaseUrl()+"/api/pay")
                    .header("api-key", credentials.getApiKey())
                    .header("secret-key", credentials.getSecretKey())
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(ElectricityPaymentResponse.class)
                    .block();
        log.info(paymentResponse);
        if (!Objects.equals(paymentResponse.getCode(), "000")) {
            log.info("FAILED: DATA NOT SAVED TO DB");
            throw new TransactionException(paymentResponse.getCode(),paymentResponse.getResponse_description(),request.getRequest_id());
        }  else {
            response = transactionService.saveTransaction(responseMapper.mapperToDB(responseMapper.mapToElectricBillResponse(request, paymentResponse)));
            log.info("SUCCESSFUL: DATA SAVED TO DB");
        }
        return response;
    }
    private VerifyMeterNumberResponse verifyMeterNumber(VerifyMeterNumberRequest request) {
        return webClient.build().post()
                .uri(credentials.getBaseUrl()+"/api/merchant-verify")
                .header("api-key", credentials.getApiKey())
                .header("secret-key", credentials.getSecretKey())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(VerifyMeterNumberResponse.class)
                .block();
    }
}
