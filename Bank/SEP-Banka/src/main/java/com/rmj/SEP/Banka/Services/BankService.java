package com.rmj.SEP.Banka.Services;

import com.rmj.SEP.Banka.models.BankAccount;

public interface BankService {
    void add(int accNumber, int pin, int amount, String name, String surname);
}
