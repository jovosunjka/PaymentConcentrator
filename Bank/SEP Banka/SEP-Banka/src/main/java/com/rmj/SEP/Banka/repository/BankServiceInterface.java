package com.rmj.SEP.Banka.repository;

import com.rmj.SEP.Banka.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankServiceInterface extends JpaRepository<BankAccount,Long> {

}
