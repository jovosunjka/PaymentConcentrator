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
    public void add(int accountNumber, int cardNumber, int securityCode, int amount, String cardHolder,String expirationDate) {
        BankAccount bankAccount = new BankAccount(accountNumber,cardNumber,securityCode,amount,cardHolder,expirationDate);

        if(serviceRepo.exists((Example.of(bankAccount)))){
            throw new AlredyExistException(String.format("There is already bank account with that number:" + bankAccount.getCardNumber()));
        }
        serviceRepo.save(bankAccount);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void OnStart()
    {
        add(11111111,1234567890,123,2500, "Igor Resman", "08/21");
        add(22222222,987654321,432,90000, "Marko Mijatovic", "09/21");
        add(33333333,1357902468,124,70000,"Jovo Sunjka", "10/21");

    }
}
