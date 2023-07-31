package com.neptunesoftware.vtpassintegration.transaction.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
@Data
@Builder
public class TransactionRequest{
        @NonNull
        private String code;
        @NonNull
        private String requestId;
        @NonNull
        private String serviceId;
        @NonNull
        private String tranId;
        @NonNull
        private String tranAppl;
        @NonNull
        private String tranSender;
        @NonNull
        private String tranAmount;
        private String tranReceiver;
        private String tranStatus;
        private String tranMethod;
        private String narration;
        private String tranPurpose;
        private String channelName;
        private String isReversal;
        private String tranDate;
        private String tranType;
        private String paymentCurr;
        private String chargeAmount;
        private String taxAmount;
}
