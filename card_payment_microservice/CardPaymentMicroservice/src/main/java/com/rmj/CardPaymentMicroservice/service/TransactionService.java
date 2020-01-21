package com.rmj.CardPaymentMicroservice.service;


import com.rmj.CardPaymentMicroservice.model.Transaction;


public interface TransactionService {
	
    Transaction save(Transaction transaction);
    
    Transaction getTransaction(Long id);
}
