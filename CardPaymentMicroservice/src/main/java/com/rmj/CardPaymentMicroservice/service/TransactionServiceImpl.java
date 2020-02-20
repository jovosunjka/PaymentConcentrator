package com.rmj.CardPaymentMicroservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmj.CardPaymentMicroservice.model.Transaction;
import com.rmj.CardPaymentMicroservice.repository.TransactionRepository;


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


   
}
