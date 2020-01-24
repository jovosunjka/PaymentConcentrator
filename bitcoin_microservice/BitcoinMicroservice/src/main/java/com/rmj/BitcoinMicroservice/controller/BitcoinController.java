package com.rmj.BitcoinMicroservice.controller;


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
import com.rmj.BitcoinMicroservice.models.Order;
import com.rmj.BitcoinMicroservice.models.PaymentResponse;
import com.rmj.BitcoinMicroservice.repository.OrderRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/bitcoin")
public class BitcoinController {

	@Autowired
	private OrderRepository orderRepository;	

    @RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody Integer amount)
    {
        return new ResponseEntity(HttpStatus.CREATED);
    }
    

    @RequestMapping(path = "/createPay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedirectUrlDTO>  pay(@RequestParam String username) {
    	
        String returnUrl = this.createPayment(username);
        
    	return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(returnUrl), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/allOrders", method = RequestMethod.GET)
    public ResponseEntity allOrders() {
    	
    	return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }
    
    public String createPayment(String username) {
    	Order ord = new Order();
		RestTemplate temp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Token " + "1ptysoycHEj2P-zsExysyx6uGkEyje2C42tspmsc");

		String randomToken = UUID.randomUUID().toString();

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		
		map.add("order_id", "CGORDER-12345");
		map.add("price_amount", "100005");
		map.add("price_currency", "USD");
		map.add("receive_currency", "USD");
		map.add("cancel_url", "http://example.com/account/orders.");	//dodati odgovaraju endpoint na koji 
		map.add("success_url", "http://example.com/account/orders.");
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
		 		
        return returnUrl;
	}
    
}
