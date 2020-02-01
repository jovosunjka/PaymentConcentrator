package com.rmj.SEP.Banka.Services;

import com.rmj.SEP.Banka.dto.*;
import com.rmj.SEP.Banka.models.BankAccount;
import com.rmj.SEP.Banka.models.Currency;
import com.rmj.SEP.Banka.models.Transaction;
import com.rmj.SEP.Banka.models.TransactionStatus;
import com.rmj.SEP.Banka.repository.BankAccountRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.rmj.SEP.Banka.websockets.dto.WebSocketMessageDTO;
import com.rmj.SEP.Banka.websockets.messaging.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    
    @Value("${bin}")
    private int bankBin;
    
    @Value("${redirect-url}")
    private String redirectUrl;

	@Value("${frontend.redirect-url}")
	private String redirectUrlForFrontend;

	@Value("${server.port}")
	private int serverPort;
    
    @Value("${response-url}")
    private String responseUrl;
    
    @Autowired
	private RestTemplate restTemplate;

    @Autowired
    private TransactionService transactionService;

	@Autowired
	private Producer producer;

    private boolean sleep = false;


	@EventListener(ApplicationReadyEvent.class)
	public void runOnApplicationReadyEvent() {
		removeBankAccountsThatNotBelongToThisBank();
    	processTransacctions();
	}

    private void processTransacctions() {
		sleep = false;
		while (true) {
			System.out.println("Loading transactions ...");
			List<Transaction> tenTransactions = transactionService.getTenTransactions();

			if (tenTransactions.isEmpty()) {
				sleep = true;
				break;
			}

			System.out.println("Processing transactions ...");
			tenTransactions.stream()
					.forEach(transaction -> {
						transaction.setStatus(TransactionStatus.PENDING);
						transactionService.save(transaction);

						int bin = getBin(transaction.getConsumerCardNumber());
						boolean response = checkBin(bin);

						if(response) {
							processTransactionForAccountFromThisBank(transaction);
						}
						else {
							BankAccountDTO consumerDTO = new BankAccountDTO(transaction.getConsumerCardHolder(),
									transaction.getConsumerExpDate(), transaction.getConsumerCardNumber(), transaction.getConsumerSecurityCode());
							try {
								redirectToPcc(transaction.getId(), transaction.getTimestamp(), transaction.getAmount(), transaction.getCurrency(), bin, consumerDTO);
							} catch (Exception e) {
								transaction.setStatus(TransactionStatus.FAIL);
								transactionService.save(transaction);

								sendTransactionCompletedDtoToCPMS(transaction.getMerchantOrderId(), transaction.getId(),
									transaction.getTimestamp(), transaction.getStatus(), transaction.getCallbackUrl(),
										transaction.getRedirectUrl());
							}
						}
					});
		}
    }

	private void removeBankAccountsThatNotBelongToThisBank() {

		//NE ZNAM DA LI POSTOJI PRAKTICNIJE RESENJE AKO POSTOJI JOVO MENJAJ
		List<BankAccount> accounts = bankAccountRepository.findAll();

		for(BankAccount a : accounts)
		{
			int accBin = getBin(a.getCardNumber());
			if(accBin != bankBin)
				bankAccountRepository.delete(a);
		}
	}

    private void processTransactionForAccountFromThisBank(Transaction transaction) {
		TransactionStatus status;

		boolean sendToPCC = true;

		try {
			BankAccount consumerAccount = getBankAccount(transaction.getConsumerCardNumber());
			boolean checked = checkAccount(consumerAccount, transaction.getConsumerCardHolder(),
					transaction.getConsumerSecurityCode(), transaction.getAmount());
			BankAccount magazineAccount= transaction.getMagazineAccount();
			if (magazineAccount != null) { // ovo je transakcija gde su acquirerBank i issuerBank razlicite
				sendToPCC = false;
			}

			if (checked) {
				consumerAccount.setAmount(consumerAccount.getAmount() - transaction.getAmount());
				if (magazineAccount != null) { // ovo je transakcija gde su acquirerBank i issuerBank razlicite
					magazineAccount.setAmount(magazineAccount.getAmount() + transaction.getAmount());
					// TODO mozda bude trebalo save-ovati magazineAccount ako to ne bude odradjeno save-ovanjem transakcije
				}
				status = TransactionStatus.SUCCESS;
			}
			else {
				status = TransactionStatus.FAIL;
			}
		} catch (Exception e) {
			status = TransactionStatus.FAIL;
		}

		transaction.setStatus(status);
		transactionService.save(transaction);

		if (sendToPCC) {
			sendTransactionCompletedDtoToPCC(transaction.getMerchantOrderId(), transaction.getMerchantTimestamp(),
					transaction.getId(), transaction.getTimestamp(), transaction.getStatus(), transaction.getCallbackUrl());
		}
		else {
			sendTransactionCompletedDtoToCPMS(transaction.getMerchantOrderId(), transaction.getId(),
				transaction.getTimestamp(), transaction.getStatus(), transaction.getCallbackUrl(), transaction.getRedirectUrl());
		}

    }

	private void sendTransactionCompletedDtoToCPMS(Long merchantOrderId, Long acquirerOrderId,
				   LocalDateTime acquirerTimeStamp,  TransactionStatus status, String callbackUrl, String redirectUrl) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		TransactionCompletedForCpmsDTO transactionCompletedForCpmsDTO = new TransactionCompletedForCpmsDTO(merchantOrderId,
																	acquirerOrderId, acquirerTimeStamp, status);
		HttpEntity<TransactionCompletedForCpmsDTO> httpEntity =
				new HttpEntity<TransactionCompletedForCpmsDTO>(transactionCompletedForCpmsDTO ,headers);
		restTemplate.exchange(callbackUrl, HttpMethod.PUT, httpEntity, Void.class);

		producer.sendMessage(new WebSocketMessageDTO(status.name(), redirectUrl)); // send redirect url on bank frontend
	}


    private void sendTransactionCompletedDtoToPCC(Long acquirerOrderId, LocalDateTime acquirerTimeStamp, Long issuerOrderId,
												  LocalDateTime issuerTimeStamp, TransactionStatus status, String callbackUrl) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		TransactionCompletedDTO transactionCompletedDTO = new TransactionCompletedDTO(acquirerOrderId, acquirerTimeStamp,
																			issuerOrderId, issuerTimeStamp, status);
		HttpEntity<TransactionCompletedDTO> httpEntity = new HttpEntity<TransactionCompletedDTO>(transactionCompletedDTO ,headers);
		restTemplate.exchange(callbackUrl, HttpMethod.PUT, httpEntity, Void.class);
	}

    private boolean checkAccount(BankAccount bankAccount, String cardHolder, int securityCode, double amount) {
    	if (!bankAccount.getCardHolder().equals(cardHolder)) {
    		return false;
		}

    	if (bankAccount.getSecurityCode() != securityCode) {
    		return false;
		}

    	if (bankAccount.getAmount() < amount) {
    		return false;
		}

    	return true;
	}

	private int getBin(long cardNumber) {
		return (int)(cardNumber / 100000);
	}

	private boolean checkBin(int cardBin) {
		if(cardBin == bankBin) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void redirectToPcc(Long transactionId, LocalDateTime timeStamp, double amount, Currency currency,
							  int acquirerBin, BankAccountDTO user) {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        BankToPccDTO bankToPccDTO = new BankToPccDTO(transactionId, timeStamp, amount, currency, acquirerBin, user);
        HttpEntity<BankToPccDTO> httpEntity = new HttpEntity<BankToPccDTO>(bankToPccDTO,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(redirectUrl,HttpMethod.POST,httpEntity,String.class);
		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			throw new RuntimeException("redirect to pcc error");
		}
    }

	@Override
	public BankAccount getBankAccount(Long cardNumber) {
		return bankAccountRepository.findByCardNumber(cardNumber)
				.orElseThrow(()-> new RuntimeException("Bank account with card number " + cardNumber + " did not found"));
	}

	@Override
	public BankAccount getBankAccount(String merchantId) {
		return bankAccountRepository.findByMerchantId(merchantId)
				.orElseThrow(()-> new RuntimeException("Bank account with merchant id " + merchantId + " did not found"));
	}

	@Override
	public void response(TransactionCompletedDTO transactionCompletedDTO) {
		Transaction transaction = transactionService.getTransaction(transactionCompletedDTO.getAcquirerOrderId());
		BankAccount magazineAccount = transaction.getMagazineAccount();
		magazineAccount.setAmount(magazineAccount.getAmount() + transaction.getAmount());
		transaction.setStatus(transactionCompletedDTO.getStatus());
		transactionService.save(transaction);

		sendTransactionCompletedDtoToCPMS(transaction.getMerchantOrderId(),
				transactionCompletedDTO.getAcquirerOrderId(), transactionCompletedDTO.getAcquirerTimeStamp(),
				transactionCompletedDTO.getStatus(), transaction.getCallbackUrl(), transaction.getRedirectUrl());
	}

	@Override
	public void save(BankAccount account) {
		bankAccountRepository.save(account);
	}

	private void cardIsExpired(String expDate) {
		LocalDate date = LocalDate.now();
		int year = date.getYear()%100;
		int month = date.getMonthValue();

		String[] dateSplit = expDate.split("/");
		int expirationYear = Integer.parseInt(dateSplit[1]);
		int expirationMonth = Integer.parseInt(dateSplit[0]);


		if(expirationYear < year) {
			throw new RuntimeException("Card is expired");
		}
		else if(year == expirationYear) {
			if (expirationMonth < month) {
				throw new RuntimeException("Card is expired");
			}
		}

	}

	@Override
	public String makeTransaction(String merchantId, String merchantPassword, Long merchantOrderId,
			  double amount, Currency currency, LocalDateTime merchantTimestamp, String redirectUrl, String callbackUrl) {
    	BankAccount magazineAccount = getBankAccount(merchantId);
    	if (!magazineAccount.getMerchantPassword().equals(merchantPassword)) {
    		throw new RuntimeException("Invalid merchant password: " + merchantPassword);
		}

		Transaction transaction = new Transaction(merchantOrderId, magazineAccount,
				amount, currency, merchantTimestamp, redirectUrl, callbackUrl);
		transaction = transactionService.save(transaction);

		return redirectUrlForFrontend  + "/" + transaction.getId() + "?amount=" + amount + "&serverPort=" + serverPort;
    }

	@Override
	public void pay(Long transactionId, BankAccountDTO bankAccountDTO) {
		cardIsExpired(bankAccountDTO.getExpDate());

		Transaction transaction = transactionService.getTransaction(transactionId);
		transaction.setConsumerCardHolder(bankAccountDTO.getCardHolder());
		transaction.setConsumerCardNumber(bankAccountDTO.getCardNumber());
		transaction.setConsumerExpDate(bankAccountDTO.getExpDate());
		transaction.setConsumerSecurityCode(bankAccountDTO.getSecurityCode());
		transaction.setStatus(TransactionStatus.READY);

		transactionService.save(transaction);

		if (sleep) {
			processTransacctions();
		}
	}

	@Override
	public void request(Long acquirerOrderId, double amount, Currency currency, LocalDateTime merchantTimestamp,
						BankAccountDTO user, String callbackUrl, String redirectUrl) {
		Transaction transaction = new Transaction(acquirerOrderId, null,
				amount, currency, merchantTimestamp, user.getCardHolder(), user.getCardNumber(),
				user.getExpDate(), user.getSecurityCode(), redirectUrl, callbackUrl);
		transactionService.save(transaction);

		if (sleep) {
			processTransacctions();
		}
	}

}
