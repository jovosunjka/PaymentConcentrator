package com.rmj.BitcoinMicroservice.controller;


import java.lang.ProcessBuilder.Redirect;
import java.time.LocalDate;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.rmj.BitcoinMicroservice.dto.RedirectUrlDTO;
import com.rmj.BitcoinMicroservice.models.CoinGateCallback;
import com.rmj.BitcoinMicroservice.models.Order;
import com.rmj.BitcoinMicroservice.models.PaymentResponse;
import com.rmj.BitcoinMicroservice.repository.OrderRepository;
import com.rmj.BitcoinMicroservice.service.PaymentService;


@CrossOrigin
@RestController
@RequestMapping(value = "/bitcoin")
public class BitcoinController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private OrderRepository orderRepository;	

    @RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody Integer amount)
    {
        return new ResponseEntity(HttpStatus.CREATED);
    }
    

    @RequestMapping(path = "/createPay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedirectUrlDTO>  pay(@RequestParam String currency, @RequestParam Integer amount) {
    	
    	RedirectUrlDTO redirectDto = this.createPayment(currency, amount);
        
    	return new ResponseEntity<RedirectUrlDTO>(redirectDto, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/allOrders", method = RequestMethod.GET)
    public ResponseEntity allOrders() {
    	
    	return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/successPayment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity successPayment(@RequestBody CoinGateCallback callback) {
    	
    	return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/cancelPayment", method = RequestMethod.GET)
    public ResponseEntity cancelPayment() {
    	
    	return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/checkPayment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedirectUrlDTO> checkPayment(@RequestParam Integer transactionId, @RequestParam Integer btId)
    {
    	PaymentResponse checkPay = new PaymentResponse();
    	RestTemplate temp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Token " + "1ptysoycHEj2P-zsExysyx6uGkEyje2C42tspmsc");
		String status = "";
		
		try {
			//ResponseEntity<PaymentResponse> response = temp.getForEntity("https://api-sandbox.coingate.com/v2/orders/" + transactionId, headers, PaymentResponse.class);
			ResponseEntity<PaymentResponse> entity = new RestTemplate().exchange("https://api-sandbox.coingate.com/v2/orders/" + btId, HttpMethod.GET, new HttpEntity<Object>(headers),PaymentResponse.class);
			checkPay.setId(entity.getBody().getId());
			checkPay.setStatus(entity.getBody().getStatus());
		} catch (HttpStatusCodeException exception) {
            System.out.println("Error bitcoin server");
        }
		
		Order oldOrder = orderRepository.findById(checkPay.getId()).get();
		if(!oldOrder.getStatus().equals(checkPay.getStatus())) {
			oldOrder.setStatus(checkPay.getStatus());
			orderRepository.save(oldOrder);
			if(checkPay.getStatus().equals("paid")) {
				status = "SUCCESS";				
			}else {
				status = "FAIL";
			}
		}else {
			oldOrder.setStatus("Expired");
			orderRepository.save(oldOrder);
			status = "FAIL";
		}
		Long id = Long.valueOf(transactionId);
		System.out.println(id);
		String frontendUrl = paymentService.pay(id, status);
				
    	System.out.println(frontendUrl);
    	
        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }
    
    public RedirectUrlDTO createPayment(String currency, Integer amount) {
    	Order ord = new Order();
		RestTemplate temp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Token " + "1ptysoycHEj2P-zsExysyx6uGkEyje2C42tspmsc");

		double cena = 0;
		if(currency.equals("RSD")){
			cena = Math.floor(amount/106);
			amount = (int)Math.round(cena);
			currency = "USD";
	    }
		System.out.println(amount);
		String randomToken = UUID.randomUUID().toString();

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		
		map.add("order_id", "CGORDER-12345");
		map.add("price_amount", amount.toString());
		map.add("price_currency", currency);
		map.add("receive_currency", "USD");
		map.add("callback_url", "");
		map.add("cancel_url", "");	//dodati odgovaraju endpoint na koji 
		map.add("success_url", "");
		//postoji i callback url opcija
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		String returnUrl = "";
		try {
			ResponseEntity<PaymentResponse> response = temp.postForEntity("https://api-sandbox.coingate.com/v2/orders", request, PaymentResponse.class);
			
            returnUrl = response.getBody().getPayment_url();
            ord.setToken(response.getBody().getToken());
            ord.setId(response.getBody().getId());
            ord.setOrder_id(response.getBody().getOrder_id());
            ord.setPrice_amount(response.getBody().getPrice_amount());
            ord.setPrice_currency(response.getBody().getPrice_currency());
            
            ord.setReceive_amount(response.getBody().getReceive_amount());
            ord.setReceive_currency(response.getBody().getReceive_currency());
            ord.setStatus(response.getBody().getStatus());
            ord.setCreatedAt(LocalDate.now().toString());
            ord.setId(response.getBody().getId());
            
            orderRepository.save(ord);
            
		} catch (HttpStatusCodeException exception) {
            System.out.println("Error bitcoin server");
        }	

		//Potrebno je ponovo pronaci iz baze kreirani order i proveriti da li je zavrsen, status PAID i azurirati ga
		/*
		 * RestTemplate temp2 = new RestTemplate(); try { ResponseEntity<Order> response
		 * = temp2.exchange("https://api-sandbox.coingate.com/v2/orders/" + ord.getId(),
		 * HttpMethod.GET, new HttpEntity<Object>(headers), Order.class);
		 * 
		 * if (!response.getBody().getStatus().equals("paid")) { ord.setStatus("paid");
		 * temp.getForEntity(ord.getCallbackUrl(), String.class); } //potrebno je
		 * sacuvati order u bazu }
		 * 
		 * } catch (HttpStatusCodeException exception) {
		 * System.out.println("Error while checking bitcoin order!"); }
		 */
		 		
        return new RedirectUrlDTO(returnUrl, ord.getId());
	}
    
}
