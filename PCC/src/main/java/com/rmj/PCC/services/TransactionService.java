package com.rmj.PCC.services;


import com.rmj.PCC.models.Transaction;

import java.time.LocalDateTime;
import java.util.List;


public interface TransactionService {
	
    Transaction save(Transaction transaction);
    
    Transaction getTransaction(Long id);

    List<Transaction> getTenTransactions();

    Transaction getTransaction(Long acquirerOrderId, LocalDateTime acquirerTimeStamp);
}
