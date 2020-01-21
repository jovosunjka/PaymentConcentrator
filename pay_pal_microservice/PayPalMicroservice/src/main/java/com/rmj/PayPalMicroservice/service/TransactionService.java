package com.rmj.PayPalMicroservice.service;


import com.rmj.PayPalMicroservice.model.Transaction;


public interface TransactionService {
	
    Transaction save(Transaction transaction);
    
    Transaction getTransaction(Long id);
}
