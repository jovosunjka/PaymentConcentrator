package com.rmj.SEP.Banka.Services;


import com.rmj.SEP.Banka.models.Transaction;
import com.rmj.SEP.Banka.models.TransactionStatus;
import com.rmj.SEP.Banka.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

	@Override
	public Transaction save(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction getTransaction(Long id) {
		return transactionRepository.findById(id).orElse(null);
	}

	@Override
	public List<Transaction> getTenTransactions() {
		return transactionRepository.findTop10ByStatusOrderByTimestampAsc(TransactionStatus.READY);
	}


   
}
