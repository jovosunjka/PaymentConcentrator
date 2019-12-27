package com.rmj.PaymentMicroservice.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.rmj.PaymentMicroservice.dto.PaymentTypeDTO;
import com.rmj.PaymentMicroservice.dto.RedirectUrlDTO;
import com.rmj.PaymentMicroservice.model.Currency;
import com.rmj.PaymentMicroservice.model.Transaction;
import com.rmj.PaymentMicroservice.model.TransactionStatus;

@Service
public class PaymentServiceImpl implements PaymentService {
;
    
	@Value("${proxy-server.url}")
	private String proxyServerUrl;
	
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
		Transaction transaction = new Transaction(merchantOrderId, amount, currency, merchantTimestamp, LocalDateTime.now(),
													redirectUrl, callbackUrl, TransactionStatus.PENDING);
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
	public String getMicroserviceFrontendUrl(String paymentType) {
		String microserviceBackendUrl = proxyServerUrl.concat("/").concat(paymentType).concat("/payment/frontend-url");
		ResponseEntity<RedirectUrlDTO> responseEntity = restTemplate.getForEntity(microserviceBackendUrl, RedirectUrlDTO.class);
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

}
