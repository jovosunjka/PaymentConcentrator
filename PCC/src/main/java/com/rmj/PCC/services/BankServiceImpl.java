package com.rmj.PCC.services;

import com.rmj.PCC.dto.BankToPccDTO;
import com.rmj.PCC.dto.BankAccountDTO;
import com.rmj.PCC.dto.PccToBankDTO;
import com.rmj.PCC.dto.TransactionCompletedDTO;
import com.rmj.PCC.models.Bank;
import com.rmj.PCC.models.Currency;
import com.rmj.PCC.models.Transaction;
import com.rmj.PCC.models.TransactionStatus;
import com.rmj.PCC.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;
    
    @Autowired
	private RestTemplate restTemplate;

    @Autowired
    private TransactionService transactionService;

    private boolean sleep = false;


    /*@EventListener(ApplicationReadyEvent.class)
    public void runOnApplicationReadyEvent() {
        processTransacctions();
    }*/

    private void processTransacctions() {
        sleep = false;
        //while (true) {
            System.out.println("Loading transactions ...");
            List<Transaction> tenTransactions = transactionService.getTenTransactions();

            if (tenTransactions.isEmpty()) {
                sleep = true;
                //break;
            }

            System.out.println("Processing transactions ...");
            tenTransactions.stream()
                    .forEach(transaction -> {
                        try {
                            //transaction.setStatus(TransactionStatus.PENDING);
                            //transactionService.save(transaction);

                            redirectToBank(transaction.getIssuerBank().getRedirectUrl(), transaction.getAcquirerOrderId(),
                                    transaction.getAcquirerBankTimestamp(), transaction.getAmount(), transaction.getCurrency(),
                                    new BankAccountDTO(transaction.getConsumerCardNumber(),
                                            transaction.getConsumerSecurityCode(), transaction.getConsumerCardHolder(),
                                            transaction.getConsumerExpDate()));
                        } catch (Exception e) {
                            sendTransactionCompletedDTO(transaction.getAcquirerOrderId(), transaction.getAcquirerBankTimestamp(),
                                    null, null, TransactionStatus.FAIL,
                                    transaction.getAcquirerBank().getTransactionCompletedUrl());
                        }
                    });
        //}
    }

    private void sendTransactionCompletedDTO(Long acquirerOrderId, LocalDateTime acquirerTimeStamp, Long issuerOrderId,
                                             LocalDateTime issuerTimeStamp, TransactionStatus status, String callbackUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        TransactionCompletedDTO transactionCompletedDTO = new TransactionCompletedDTO(acquirerOrderId, acquirerTimeStamp, issuerOrderId, issuerTimeStamp, status);
        HttpEntity<TransactionCompletedDTO> httpEntity = new HttpEntity<TransactionCompletedDTO>(transactionCompletedDTO ,headers);
        restTemplate.exchange(callbackUrl, HttpMethod.PUT, httpEntity, Void.class);
    }

    @Override
    public void sendTransactionCompletedDTO(TransactionCompletedDTO transactionCompletedDTO) {
        Transaction transaction = transactionService.getTransaction(transactionCompletedDTO.getAcquirerOrderId(),
                transactionCompletedDTO.getAcquirerTimeStamp());
        transaction.setIssuerOrderId(transactionCompletedDTO.getIssuerOrderId());
        transaction.setIssuerBankTimestamp(transactionCompletedDTO.getIssuerTimeStamp());
        transaction.setStatus(transactionCompletedDTO.getStatus());
        transactionService.save(transaction);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TransactionCompletedDTO> httpEntity = new HttpEntity<TransactionCompletedDTO>(transactionCompletedDTO ,headers);
        restTemplate.exchange(transaction.getAcquirerBank().getTransactionCompletedUrl(), HttpMethod.PUT, httpEntity, Void.class);
    }

    @Override
    public void saveBank(String name, int bin, String redirectUrl, String transactionCompletedUrl) {
        bankRepository.save(new Bank(name, bin, redirectUrl, transactionCompletedUrl));
    }

    @Override
    public List<Bank> getAllBanks() {
        List<Bank> banks = new ArrayList<>();
        banks = bankRepository.findAll();
        return banks;
    }

	@Override
	public void AddNewBank(Bank newBank) {
		bankRepository.save(newBank);
	}

	@Override
	public void redirectToBank(String issuerBankUrl, Long acquirerOrderid, LocalDateTime acquirerTimeStamp,
                                 double amount, Currency currency, BankAccountDTO user) {
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        PccToBankDTO pccToBankDTO = new PccToBankDTO(acquirerOrderid, acquirerTimeStamp, amount, currency, user);
        HttpEntity<PccToBankDTO> httpEntity = new HttpEntity<PccToBankDTO>(pccToBankDTO,headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(issuerBankUrl,HttpMethod.POST,httpEntity,Void.class);

        if(responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("redirect from pcc to bank2 error");
        }
	}

	@Override
	public Bank getBank(int bin) {
		return bankRepository.findByBin(bin)
				.orElseThrow(()-> new RuntimeException("Bank (bin = "+ bin +") did not found"));
	}

    @Override
    public void makeTransaction(Long transactionId, LocalDateTime timeStamp, double amount, Currency currency, int acquirerBin, BankAccountDTO user) {
        Bank acquirerBank = getBank(acquirerBin);

        int issuerBin = getBin(user.getCardNumber());
        Bank issuerBank = getBank(issuerBin);

        Transaction transaction = new Transaction(transactionId, user.getCardHolder(), user.getExpDate(),
                user.getCardNumber(), user.getSecurityCode(), amount, currency, timeStamp, issuerBank, acquirerBank);
        transactionService.save(transaction);

        //if (sleep) {
            processTransacctions();
        //}
    }

    private int getBin(long cardNumber) {
        return (int)(cardNumber / 100000);
    }

}
