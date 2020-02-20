package com.rmj.PaymentMicroservice.service;


import com.rmj.PaymentMicroservice.dto.LoginUserDTO;
import com.rmj.PaymentMicroservice.dto.RegistrationPaymentAccountDTO;
import com.rmj.PaymentMicroservice.dto.TokenDTO;
import com.rmj.PaymentMicroservice.model.Currency;
import com.rmj.PaymentMicroservice.model.PaymentAccount;
import com.rmj.PaymentMicroservice.repository.PaymentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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

    /*@EventListener(ApplicationReadyEvent.class)
    public void writeEntitiesInDBWithEncryptedSomeAttributes() {
        paymentAccountRepository.save(new PaymentAccount("paypal", "paypal_magazine1", "g$b)x+3sJ4", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("paypal", "paypal_magazine2", "gmJ(v2=THP", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("paypal", "paypal_magazine3", "@z]m5P(.HG", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("bitcoin", "bitcoin_magazine4", "5]D4}Mw4s<", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("bitcoin", "bitcoin_magazine5", "Epr8^n}E?=", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("bitcoin", "bitcoin_magazine6", "<Kg8bPZk%[", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("card-payment", "card-payment_magazine7", "5NMf@NENv]", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("card-payment", "card-payment_magazine8", "}}qHA37DsG", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("card-payment", "card-payment_magazine9", "?S/3/phh[^", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("paypal", "paypal_magazine10", "A5^:bJwe:\\", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("paypal", "paypal_magazine12", "^ZHh9U<J,K", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("paypal", "paypal_magazine11", "Ag+hj2:@rQ", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("bitcoin", "bitcoin_magazine13", "8aCL!X\\xs7", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("bitcoin", "bitcoin_magazine14", "fGXWJ$,g7K", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("bitcoin", "bitcoin_magazine15", "ftDF_T6%Bk", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("card-payment", "card-payment_magazine16", "~8Z6ycq9~s", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("card-payment", "card-payment_magazine17", "_,M>d,3&/y", Currency.RSD));
        paymentAccountRepository.save(new PaymentAccount("card-payment", "card-payment_magazine18", "r9PJEn\"\"}q$", Currency.RSD));
    }*/

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
