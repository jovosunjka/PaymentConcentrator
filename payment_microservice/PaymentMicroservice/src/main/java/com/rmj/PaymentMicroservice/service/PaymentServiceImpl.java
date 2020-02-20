package com.rmj.PaymentMicroservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.rmj.PaymentMicroservice.exception.NotFoundException;
import com.rmj.PaymentMicroservice.exception.RequestTimeoutException;
import com.rmj.PaymentMicroservice.exception.UserNotFoundException;
import com.rmj.PaymentMicroservice.model.*;
import com.rmj.PaymentMicroservice.dto.TransactionCompletedDTO;
import com.rmj.PaymentMicroservice.dto.FormFieldsForPaymentTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.rmj.PaymentMicroservice.dto.PayDTO;
import com.rmj.PaymentMicroservice.dto.PaymentTypeDTO;
import com.rmj.PaymentMicroservice.dto.RedirectUrlDTO;

@Service
public class PaymentServiceImpl implements PaymentService {
    
	@Value("${proxy-server.url}")
	private String proxyServerUrl;
	
	@Value("${transaction-completed.url}")
	private String transactionCompletedUrl;
	
    @Autowired
    private EurekaClient eurekaClient;
    
    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FormFieldsForPaymentTypeService formFieldsForPaymentTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentAccountService paymentAccountService;

    
    @Override
    public List<PaymentTypeDTO> getCurrentlyActivatedPaymentTypes(List<PaymentAccount> accounts) {
        List<String> paymentTypeMicroserviceNames = getPaymentTypeNames();
        
        List<String> paymentTypeNames = paymentTypeMicroserviceNames.stream()
        				.map(name -> name.toLowerCase().replace("pt_", "")
												.replace("-microservice", ""))
        				.collect(Collectors.toList());

        return accounts.stream()
				.map(account -> new PaymentTypeDTO(account.getType(), paymentTypeNames.contains(account.getType())))
				.collect(Collectors.toList());
    }

	@Override
	public List<String> getPaymentTypeNames() {
		List<Application> applications = eurekaClient.getApplications().getRegisteredApplications();
		if (applications.isEmpty()) {
			return Collections.emptyList();
		}

		return applications.stream()
				.filter(app -> app.getName().toLowerCase().startsWith("pt_"))
				.map(app -> app.getName())
				.collect(Collectors.toList());
	}

	@Override
	public Transaction pay(Long merchantOrderId, double amount, Currency currency, LocalDateTime merchantTimestamp,
			String redirectUrl, String callbackUrl) {
		Transaction transaction = new Transaction(merchantOrderId, amount, currency, merchantTimestamp,
													redirectUrl, callbackUrl);
		transaction = transactionService.save(transaction);
		return transaction;
	}

	@Override
	public void saveChosenPayment(Long transactionId, String paymentType) {
		Transaction transaction = transactionService.getTransaction(transactionId);
		transaction.setPaymentType(paymentType);
		transactionService.save(transaction);
	}

	@Override
	public String getMicroserviceFrontendUrl(Long transactionId, String paymentType) throws NotFoundException, RequestTimeoutException {
    	User loggedUser = userService.getLoggedUser();
    	PaymentAccount paymentAccount = loggedUser.getAccounts().stream()
				.filter(a -> a.getType().equals(paymentType))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Payment account (type=".concat(paymentType).concat(") not found!")));

		Transaction transaction = transactionService.getTransaction(transactionId);
		String microserviceBackendUrl = proxyServerUrl.concat("/pt_").concat(paymentType).concat("-microservice/payment/frontend-url");
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("paymentAccountUsername", paymentAccount.getUsername());
        String callbackUrl = transactionCompletedUrl;

        PayDTO payDTO = new PayDTO(transaction.getId()/*transaction.getMerchantOrderId()*/, transaction.getAmount(), transaction.getCurrency(),
        		transaction.getMerchantTimestamp(), transaction.getRedirectUrl(), callbackUrl);
        HttpEntity<PayDTO> httpEntity = new HttpEntity<PayDTO>(payDTO, headers);
        ResponseEntity<RedirectUrlDTO> responseEntity = restTemplate.exchange(microserviceBackendUrl, HttpMethod.POST, httpEntity, RedirectUrlDTO.class);
        HttpStatus statusCode = responseEntity.getStatusCode();

        if (statusCode == HttpStatus.GATEWAY_TIMEOUT || statusCode == HttpStatus.REQUEST_TIMEOUT) {
            throw new RequestTimeoutException();
        }

        if (paymentAccount.getAccessToken() == null) {
        	paymentAccount = paymentAccountService.getPaymentAccount(paymentAccount.getId());
        	// osvezavamo
		}
        String url = responseEntity.getBody().getRedirectUrl();
        if (url == null) {
        	url = "https://localhost:8081_science_center_1/transactions_page";
		}
        return url + "&token=" + paymentAccount.getAccessToken();
	}

	@Override
	public void transactionCompleted(long transactionId, String status) {
		Transaction transaction = transactionService.getTransaction(transactionId);
		TransactionStatus statusEnum = TransactionStatus.valueOf(status.toUpperCase());
		transaction.setStatus(statusEnum);
		transactionService.save(transaction);
	
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<TransactionCompletedDTO> httpEntity2 = new HttpEntity<TransactionCompletedDTO>(new TransactionCompletedDTO(transaction.getMerchantOrderId(), status), headers);
    	restTemplate.exchange(transaction.getCallbackUrl(), HttpMethod.PUT, httpEntity2, Void.class);
		
	}

	@Override
	public List<Transaction> getTransactions(String[] merchantOrderIds) {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		Arrays.stream(merchantOrderIds)
				.forEach(merchantOrderId -> {
					Transaction transaction = transactionService.getTransactionByMerchantOrderId(Long.parseLong(merchantOrderId));
					if (transaction != null) {
						transactions.add(transaction);
					}
				});
		return transactions;
	}

	@Override
	public List<FormFieldsForPaymentTypeDTO> getFormFieldsForPaymentTypes() {
		List<String> paymentTypeNames = getPaymentTypeNames();
		return paymentTypeNames.stream()
				.map(ptName -> {
					ptName = ptName.toLowerCase();
					String ptn = ptName.replace("pt_", "").replace("-microservice", "");

					FormFieldsForPaymentTypeDTO formFieldsForPaymentTypeDTO;
					FormFieldsForPaymentType formFieldsForPaymentType;
					try {
						// prvo probaj naci u lokalnoj bazi
						formFieldsForPaymentType = formFieldsForPaymentTypeService.getFormFieldsForPaymentType(ptn);
						formFieldsForPaymentTypeDTO = new FormFieldsForPaymentTypeDTO(formFieldsForPaymentType);
					} catch (Exception e) {
						// ako ne nadjes u bazi, pitaj microservice, pa onda sacuvaj u lokalnoj bazi
						formFieldsForPaymentTypeDTO = formFieldsForPaymentTypeService.getFormFieldsForPaymentTypeFromMicroservice(ptName);
						List<FormField> formFields = formFieldsForPaymentTypeDTO.getFormFields().stream()
															.map(ff -> new FormField(ff.getName(), ff.getType(), ff.getOptional()))
															.collect(Collectors.toList());
						formFieldsForPaymentType = new FormFieldsForPaymentType(formFieldsForPaymentTypeDTO.getPaymentType(), formFields);
						formFieldsForPaymentTypeService.save(formFieldsForPaymentType);
					}
					return formFieldsForPaymentTypeDTO;
				})
				.collect(Collectors.toList());
	}

	@Override
	public boolean loggedUserHasPaymentType(List<PaymentAccount> accounts, String paymentType) {
		return accounts.stream()
				.filter(acccount -> acccount.getType().equals(paymentType))
				.count() > 0;
	}

}
