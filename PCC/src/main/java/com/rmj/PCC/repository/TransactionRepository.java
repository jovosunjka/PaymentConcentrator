package com.rmj.PCC.repository;

import com.rmj.PCC.models.Transaction;
import com.rmj.PCC.models.TransactionStatus;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

    List<Transaction> findTop10ByStatusOrderByTimestampAsc(TransactionStatus status);

    Optional<Transaction> findByAcquirerOrderIdAndAcquirerBankTimestamp(Long acquirerOrderId, LocalDateTime acquirerBankTimestamp);
}
