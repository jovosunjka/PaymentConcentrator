package com.rmj.BitcoinMicroservice.service;


import com.rmj.BitcoinMicroservice.models.Transaction;


public interface TransactionService {
	
    Transaction save(Transaction transaction);
    
    Transaction getTransaction(Long id);
}
