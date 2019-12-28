package com.rmj.PaymentMicroservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.rmj.PaymentMicroservice.dto.PayDTO;
import com.rmj.PaymentMicroservice.dto.PaymentTypeDTO;
import com.rmj.PaymentMicroservice.dto.RedirectUrlDTO;
import com.rmj.PaymentMicroservice.model.Currency;
import com.rmj.PaymentMicroservice.model.Transaction;
import com.rmj.PaymentMicroservice.model.TransactionStatus;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Service
public class PaymentServiceImpl implements PaymentService {
;
    
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
    

    
    @Override
    public List<PaymentTypeDTO> getPaymentTypes() {
        // List<PaymentTypeDTO> paymentTypes = new ArrayList<PaymentTypeDTO>();
        // paymentTypes.add(new PaymentTypeDTO("aaa", "aaa_url"));
        // paymentTypes.add(new PaymentTypeDTO("aaa", "bbb_url"));
        List<Application> applications = eurekaClient.getApplications().getRegisteredApplications();
        if (applications.isEmpty()) {
        	return Collections.emptyList();
        }
        
        return applications.stream()
        				.filter(app -> app.getName().toLowerCase().startsWith("pt_"))
        				.map(app -> new PaymentTypeDTO(app.getName(), 
        										proxyServerUrl.concat("/").concat(app.getName().toLowerCase().concat("/"))))
        				.collect(Collectors.toList());
        // PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
        // ResponseEntity<PaymentTypesDTO> responseEntity = restTemplate.getForEntity(microservicesUrl, PaymentTypesDTO.class);
        // return responseEntity.getBody().getTypes();
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
	public String getMicroserviceFrontendUrl(Long transactionId, String paymentType) {
		Transaction transaction = transactionService.getTransaction(transactionId);
		//"https://localhost:8084"
		String microserviceBackendUrl = proxyServerUrl.concat("/").concat(paymentType).concat("/payment/frontend-url");
		//ResponseEntity<RedirectUrlDTO> responseEntity = restTemplate.getForEntity(microserviceBackendUrl, RedirectUrlDTO.class);
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String callbackUrl = transactionCompletedUrl;
        PayDTO payDTO = new PayDTO(transaction.getMerchantOrderId(), transaction.getAmount(), transaction.getCurrency(),
        		transaction.getMerchantTimestamp(), transaction.getRedirectUrl(), callbackUrl);
        HttpEntity<PayDTO> httpEntity = new HttpEntity<PayDTO>(payDTO, headers);
        ResponseEntity<RedirectUrlDTO> responseEntity = restTemplate.exchange(microserviceBackendUrl, HttpMethod.POST, httpEntity, RedirectUrlDTO.class);
		return responseEntity.getBody().getRedirectUrl();
	}

	@Override
	public void transactionCompleted(long transactionId, String status) {
		Transaction transaction = transactionService.getTransaction(transactionId);
		TransactionStatus statusEnum = TransactionStatus.valueOf(status);
		transaction.setStatus(statusEnum);
		transactionService.save(transaction);
		
		restTemplate.getForEntity(transaction.getCallbackUrl(), Void.class);
	}

	@Override
	public List<Transaction> getTransactions(String[] transactionIds) {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		Arrays.stream(transactionIds)
				.forEach(transactionId -> {
					Transaction transaction = transactionService.getTransaction(Long.parseLong(transactionId));
					transactions.add(transaction);
				});
		return transactions;
	}

}
