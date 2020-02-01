package com.rmj.SEP.Banka.Services;


import com.rmj.SEP.Banka.models.Transaction;

import java.util.List;


public interface TransactionService {
	
    Transaction save(Transaction transaction);
    
    Transaction getTransaction(Long id);

    List<Transaction> getTenTransactions();
}
