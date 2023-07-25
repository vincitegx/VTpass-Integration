package com.neptunesoftware.vtpassintegration.airtime.response;

import lombok.NonNull;

public class AirtimeResponse {

    @NonNull
    private String code ;

    private String response_description ;

    public String getResponse_description() {
        return response_description;
    }

    public void setResponse_description(String response_description) {
        this.response_description = response_description;
    }

    private String requestId;
    private String transactionId ;

    private String amount;

    public AirtimeResponse(String code,String response_description, String requestId, String transactionId, String amount, TransactionDate transaction_date) {
        this.code = code;
        this.response_description =response_description;
        this.requestId = requestId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.transaction_date = transaction_date;
    }

    public AirtimeResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public TransactionDate getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(TransactionDate transaction_date) {
        this.transaction_date = transaction_date;
    }

    private TransactionDate transaction_date;
}
