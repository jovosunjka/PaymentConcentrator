package com.rmj.SEP.Banka.Services;

import com.rmj.SEP.Banka.exceptions.AlredyExistException;
import com.rmj.SEP.Banka.models.BankAccount;
import com.rmj.SEP.Banka.repository.BankServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankServiceInterface serviceRepo;

    @Override
    public void add(int cardNumber, int pin, int amount, String name, String surname) {
        BankAccount bankAccount = new BankAccount(cardNumber,pin,amount,name,surname);

        if(serviceRepo.exists((Example.of(bankAccount)))){
            throw new AlredyExistException(String.format("There is already bank account with that number:" + bankAccount.getCardNumber()));
        }
        serviceRepo.save(bankAccount);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void OnStart()
    {
        add(1234567890,1234,50000,"Igor","Resman");
        add(987654321,4321,90000,"Marko","Mijatovic");
        add(1357902468,1234,70000,"Jovo","Sunjka");

    }
}
