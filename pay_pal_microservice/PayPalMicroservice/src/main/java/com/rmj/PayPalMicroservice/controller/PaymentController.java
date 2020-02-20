package com.rmj.PayPalMicroservice.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.rmj.PayPalMicroservice.dto.BillingCyclesDTO;
import com.rmj.PayPalMicroservice.dto.FixedPriceDTO;
import com.rmj.PayPalMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.PayPalMicroservice.dto.FrequencyDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.rmj.PayPalMicroservice.dto.PayDTO;
import com.rmj.PayPalMicroservice.dto.PayPalProductDTO;
import com.rmj.PayPalMicroservice.dto.PaymentPreferencesDTO;
import com.rmj.PayPalMicroservice.dto.PlanDTO;
import com.rmj.PayPalMicroservice.dto.PlanRequestDTO;
import com.rmj.PayPalMicroservice.dto.PricingSchemeDTO;
import com.rmj.PayPalMicroservice.dto.ProductDTO;
import com.rmj.PayPalMicroservice.dto.RedirectUrlDTO;
import com.rmj.PayPalMicroservice.dto.SetupFeeDTO;
import com.rmj.PayPalMicroservice.model.PayPalResponse;
import com.rmj.PayPalMicroservice.model.Plan;
import com.rmj.PayPalMicroservice.model.Product;
import com.rmj.PayPalMicroservice.model.SubscribePlanResponse;
import com.rmj.PayPalMicroservice.model.SubscribeProductResponse;
import com.rmj.PayPalMicroservice.model.Transaction;
import com.rmj.PayPalMicroservice.model.TransactionStatus;
import com.rmj.PayPalMicroservice.model.User;
import com.rmj.PayPalMicroservice.repository.PayPalResponseRepository;
import com.rmj.PayPalMicroservice.repository.PlanRepository;
import com.rmj.PayPalMicroservice.repository.ProductRepository;
import com.rmj.PayPalMicroservice.repository.TransactionRepository;
import com.rmj.PayPalMicroservice.service.PaymentService;
import com.rmj.PayPalMicroservice.service.UserService;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private PayPalResponseRepository repository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate temp;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private TransactionRepository tRepository;
	
	@Autowired 
	private PayPalResponseRepository payPalResponseRepository;
	
    @PreAuthorize("hasAuthority('PAY')")
	@RequestMapping(value = "/frontend-url", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RedirectUrlDTO> getFrontendUrl(@RequestBody PayDTO payDTO)
    {
		Long transactionId = paymentService.makeTransaction(payDTO.getMerchantOrderId(), payDTO.getAmount(), payDTO.getCurrency(),
														payDTO.getTimestamp(), payDTO.getRedirectUrl(), payDTO.getCallbackUrl());
		String frontendUrl = paymentService.getFrontendUrl() + "/" + transactionId + "/" + payDTO.getAmount() + "/" + payDTO.getCurrency();
        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }
	
    @RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody Integer amount)
    {
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @RequestMapping(path = "/saveTransaction", method = RequestMethod.POST)
    public ResponseEntity<RedirectUrlDTO> saveTransaction(@RequestBody PayPalResponse executeTransaction)
    {
    	Transaction t = tRepository.findById(executeTransaction.getIdPayment()).get();
    	if(t != null && t.getStatus().equals("SUCCESS")) {
    		return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(""), HttpStatus.OK);
    	}
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	
    	executeTransaction.setCreate_time(dateFormat.format(cal.getTime()).toString());
    	repository.save(executeTransaction);
    	String frontendUrl = paymentService.pay(executeTransaction.getIdPayment(), executeTransaction.getState());
    	System.out.println("Sacuvana " + executeTransaction.getState() + " paypal transakcija u bazu... url za redirect: " + frontendUrl);
        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/cancelTransaction", method = RequestMethod.POST)
    public ResponseEntity<RedirectUrlDTO> cancelTransaction(@RequestBody PayPalResponse executeTransaction)
    {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	
    	executeTransaction.setCreate_time(dateFormat.format(cal.getTime()).toString());
    	repository.save(executeTransaction);
    	String frontendUrl = paymentService.pay(executeTransaction.getIdPayment(), executeTransaction.getState());
    	System.out.println("Sacuvana neuspesna paypal transakcija u bazu");
        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/cancelTransactionCloseTab", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedirectUrlDTO> cancelTransactionCloseTab(@RequestParam Integer transactionId)
    {
    	Long id = Long.valueOf(transactionId);
    	String status = "FAIL";
    	String frontendUrl = paymentService.pay(id, status);
    	return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }

    @RequestMapping(path = "/getAllTransaction", method = RequestMethod.GET)
    public ResponseEntity getAllTransaction()
    {
    	
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/form-fields-for-payment-type", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormFieldsForPaymentTypeDTO> getFormFieldsForPaymentTypes() {
	    FormFieldsForPaymentTypeDTO formFieldsForPaymentTypeDTO = paymentService.getFormFieldsForPaymentType();
        return new ResponseEntity<FormFieldsForPaymentTypeDTO>(formFieldsForPaymentTypeDTO, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/bilosta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity bilosta(@RequestBody String s)
    {
    	
    	return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAuthority('PAY')")
    //Dobijanje producta sa paymentms(naucne centrale) i pravljenje product plana pretplate
    @RequestMapping(path = "/scientific-paper-plans", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity scientificPaperPlans(@RequestBody ProductDTO productDTO)
    {
    	User loggedUser = null;
        try {
            loggedUser = userService.getLoggedUser();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
		
    	Product product = loggedUser.getProduct(productDTO.getName());
		if (product == null) {
			 
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Basic QVN5OWZyYlUwN29PRkRObXltYTVTTUtiS0tLZWQ0dzNIQ3RFZ3MzLXRCQi1hUjhPUjVVZzFiaDN0aVFWdklGRWk1ajBDVFdzRVhVdjQxdEY6RUVrc2ZOZkpzU21BVWpXNDl3X05kdHBnNW1YQktiWUM3NHFyQ2REbkZDeWtoYXUxUUZkbkFoYjltbHdmTzhRRnhzcXM5M2V0T0tPU1F1MHE=");
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	    	
	    	PayPalProductDTO ppProductDTO = new PayPalProductDTO("Rad " + productDTO.getName(), "Proizvod", "DIGITAL", "BOOKS_AND_MAGAZINES");
			HttpEntity<PayPalProductDTO> request = new HttpEntity<PayPalProductDTO>(ppProductDTO, headers);
	    	
			//Pravljenje producta na paypal servisu
			ResponseEntity<SubscribeProductResponse> response = temp.postForEntity("https://api.sandbox.paypal.com/v1/catalogs/products", request, SubscribeProductResponse.class);
			
			product = new Product(response.getBody().getId(), productDTO.getName(), new ArrayList<Plan>());
			product = productRepository.save(product);
		}
		
		List<Plan> plans = new ArrayList<Plan>();
		
		try {	
			
			for(PlanDTO p: productDTO.getPlans()) {
				
				HashMap<String, String> fixedPrice1 = new HashMap<String, String>();
				fixedPrice1.put("value", String.valueOf(p.getPrice()));
				fixedPrice1.put("currency_code", "USD");
				
				HashMap<String, HashMap<String, String>> pricingScheme = new HashMap<String, HashMap<String, String>>();
				pricingScheme.put("fixed_price", fixedPrice1);
			
				
				HashMap<String, Object> frequency1 = new HashMap<String, Object>();
				frequency1.put("interval_unit", p.getIntervalUnit());
				frequency1.put("interval_count", Integer.parseInt(p.getIntervalCount()));
				
				List<HashMap<String, Object>> billingCycles = new ArrayList<HashMap<String,Object>>();
				
				HashMap<String, Object> billingCycles1 = new HashMap<String, Object>();
				billingCycles1.put("frequency", frequency1);
				billingCycles1.put("tenure_type", "REGULAR");
				billingCycles1.put("sequence", 1);
				billingCycles1.put("total_cycles", 12);
				billingCycles1.put("pricing_scheme", pricingScheme);
				billingCycles.add(billingCycles1);
				
				HashMap<String, String> setupFee = new HashMap<String, String>();
				setupFee.put("value", String.valueOf(p.getPrice()));
				setupFee.put("currency_code", "USD");
				
				HashMap<String, Object> paymentPreferences = new HashMap<String, Object>();
				paymentPreferences.put("auto_bill_outstanding", true);
				paymentPreferences.put("setup_fee", setupFee);
				paymentPreferences.put("setup_fee_failure_action", "CONTINUE");
				paymentPreferences.put("payment_failure_threshold", 3);
				
				HashMap<String, Object> requestPlan1 = new HashMap<String, Object>();
				requestPlan1.put("product_id", product.getSubscribeProductId());
				requestPlan1.put("name", "Magazine");
				requestPlan1.put("description", "Rad");
				requestPlan1.put("status", "ACTIVE");
				requestPlan1.put("billing_cycles", billingCycles);
				requestPlan1.put("payment_preferences", paymentPreferences);
				
				
				HttpHeaders headersPlan = new HttpHeaders();
				headersPlan.add("Authorization", "Bearer A21AAFHvG7flbvAKzrY-3ilUvfc5L50AvDcU5m5F3vDZdLEV20tvClenX7V7Jr0cnfULfKLGWq1ueFSJhDjBYl1mHWf0530zA");
				//headersPlan.setContentType(MediaType.APPLICATION_JSON);
				
				HttpEntity<HashMap<String, Object>> requestNewPlan = new HttpEntity<HashMap<String, Object>>(requestPlan1, headersPlan);
				
				ResponseEntity<HashMap> responseNewPlan = temp.postForEntity("https://api.sandbox.paypal.com/v1/billing/plans", requestNewPlan, HashMap.class);
				//listObjectsResponse.add(responseNewPlan.getBody());
				
				System.out.println("Odgovor plan: " + responseNewPlan.getBody());
				Plan planForList = new Plan((String)responseNewPlan.getBody().get("id"), p.getIntervalUnit(), Integer.parseInt(p.getIntervalCount()), p.getPrice());
				planForList = planRepository.save(planForList);
				plans.add(planForList);
				System.out.println("Save new plan");
			}
			product.getPlans().addAll(plans);
			
			productRepository.save(product);
			System.out.println("Save new product");
			//Napravljen je plan, potrebno je sacuvati plan u bazu
		} catch (HttpStatusCodeException exception) {
            System.out.println("Error paypal server");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
		System.out.println("*************************");
        return new ResponseEntity(HttpStatus.OK);
    }
    
    //Periodicna provera stanja pretplate
    @Scheduled(fixedRate = 50000)
    public void cronJob() {
    	System.out.println("----------Provera!-----------");
    	
    	List<PayPalResponse> allSubscrubes = payPalResponseRepository.findAll();
    	
    	if(!allSubscrubes.isEmpty())
    	{
    		HttpHeaders headers = new HttpHeaders();
    		headers.add("Authorization", "Bearer A21AAFHvG7flbvAKzrY-3ilUvfc5L50AvDcU5m5F3vDZdLEV20tvClenX7V7Jr0cnfULfKLGWq1ueFSJhDjBYl1mHWf0530zA");    		
    		HttpEntity<?> entity = new HttpEntity<>(headers);
    		
    		for(PayPalResponse p: allSubscrubes) 
    		{	    			
    			ResponseEntity<HashMap> responseNewPlan = temp.exchange("https://api.sandbox.paypal.com/v1/billing/subscriptions/" + p.getId(), HttpMethod.GET, entity, HashMap.class);
    			String status = (String)responseNewPlan.getBody().get("status");
    			if(!status.equals("ACTIVE")) 
    			{
    				System.out.println("Status se promenio za subscribe: " + p.getId());
    			}
    			else 
    			{
    				System.out.println("Status se za subscribe: " + p.getId() + " nije menjan");
    			}
    		}
    	}
    	else 
    	{
    		System.out.println("Nema subscrie-ra u sistemu!");
    	}
    }
}
