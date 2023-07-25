package com.neptunesoftware.vtpassintegration.transaction.repository;

import com.neptunesoftware.vtpassintegration.transaction.request.TransactionRequest;
import com.neptunesoftware.vtpassintegration.transaction.response.TransactionResponse;

public class TransactionRepository {
    
    public TransactionResponse saveTransaction(TransactionRequest transactionRequest){
        return new TransactionResponse();
    }
}
