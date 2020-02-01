package com.rmj.PCC.services;


import com.rmj.PCC.models.Transaction;
import com.rmj.PCC.models.TransactionStatus;
import com.rmj.PCC.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

	@Override
	public Transaction getTransaction(Long acquirerOrderId, LocalDateTime acquirerTimeStamp) {
		return transactionRepository.findByAcquirerOrderIdAndAcquirerBankTimestamp(acquirerOrderId, acquirerTimeStamp)
			.orElseThrow(() -> new RuntimeException("Transaction (acquirerOrderId=" +acquirerOrderId+", acquirerTimeStamp="
					+ acquirerTimeStamp + ") not found!"));
	}


}
