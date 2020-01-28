package com.rmj.PaymentMicroservice.service;


import com.rmj.PaymentMicroservice.dto.LoginUserDTO;
import com.rmj.PaymentMicroservice.dto.RegistrationPaymentAccountDTO;
import com.rmj.PaymentMicroservice.dto.TokenDTO;
import com.rmj.PaymentMicroservice.model.PaymentAccount;
import com.rmj.PaymentMicroservice.repository.PaymentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PaymentAccountServiceImpl implements PaymentAccountService {

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${proxy-server.url}")
    private String proxyServerUrl;


    @Override
    public PaymentAccount getPaymentAccount(String username) {
        return paymentAccountRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("PaymentAccount (username=".concat(username).concat(") not found!")));
    }

    @Override
    public PaymentAccount loginPaymentAccount(PaymentAccount paymentAccount) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        LoginUserDTO userDTO = new LoginUserDTO(paymentAccount.getUsername(), paymentAccount.getPassword());

        HttpEntity<LoginUserDTO> httpEntity = new HttpEntity<LoginUserDTO>(userDTO, headers);
        String loginUrl = proxyServerUrl.concat("/pt_").concat(paymentAccount.getType())
                                                            .concat("-microservice/users/login");

        ResponseEntity<TokenDTO> tokenDTOResponseEntity = restTemplate.exchange(loginUrl, HttpMethod.PUT, httpEntity, TokenDTO.class);
        if (tokenDTOResponseEntity.getStatusCode() == HttpStatus.OK) {
            String token = tokenDTOResponseEntity.getBody().getToken();
            paymentAccount.setAccessToken(token);
            paymentAccount = paymentAccountRepository.save(paymentAccount);
            System.out.println(paymentAccount.getUsername() + " login - success");
            return paymentAccount;
        }
        else {
            System.out.println(paymentAccount.getUsername() + " login - fail");
            return null;
        }
    }

    @Override
    public void registerPaymentAccount(String paymentType, RegistrationPaymentAccountDTO registrationPaymentAccountDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegistrationPaymentAccountDTO> httpEntity =
                new HttpEntity<RegistrationPaymentAccountDTO>(registrationPaymentAccountDTO, headers);
        String registrationUrl = proxyServerUrl.concat("/pt_").concat(paymentType)
                                                .concat("-microservice/users/registration");

        ResponseEntity<Void> tokenDTOResponseEntity = restTemplate.exchange(registrationUrl, HttpMethod.POST,
                httpEntity, Void.class);
        if (tokenDTOResponseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Registration on ".concat(paymentType).concat(" microservice failed"));
        }
    }

    @Override
    public PaymentAccount getPaymentAccount(Long id) {
        return paymentAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PaymentAccount (id=".concat("" + id).concat(") not found!")));
    }

}
