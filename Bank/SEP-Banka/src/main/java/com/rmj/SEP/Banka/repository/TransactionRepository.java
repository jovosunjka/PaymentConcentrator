package com.rmj.SEP.Banka.repository;

import com.rmj.SEP.Banka.models.Transaction;
import com.rmj.SEP.Banka.models.TransactionStatus;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

    List<Transaction> findTop10ByStatusOrderByTimestampAsc(TransactionStatus status);
}
