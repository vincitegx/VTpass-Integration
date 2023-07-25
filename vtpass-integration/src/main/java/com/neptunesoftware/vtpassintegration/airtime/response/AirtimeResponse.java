package com.neptunesoftware.vtpassintegration.airtime.response;

public class AirtimeResponse {

    private Integer code ;

    private String requestId;
    private Integer transactionId ;

    private Integer amount;

    public AirtimeResponse(Integer code, String requestId, Integer transactionId, Integer amount, TransactionDate transaction_date) {
        this.code = code;
        this.requestId = requestId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.transaction_date = transaction_date;
    }

    public AirtimeResponse() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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
