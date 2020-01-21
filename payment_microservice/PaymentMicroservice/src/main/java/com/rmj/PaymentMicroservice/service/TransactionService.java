package com.rmj.PaymentMicroservice.service;


import com.rmj.PaymentMicroservice.model.Transaction;


public interface TransactionService {
	
    Transaction save(Transaction transaction);
    
    Transaction getTransaction(Long id);
}
