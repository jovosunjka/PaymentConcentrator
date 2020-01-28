package com.rmj.PaymentMicroservice.config;

import com.rmj.PaymentMicroservice.model.PaymentAccount;
import com.rmj.PaymentMicroservice.service.PaymentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

// https://www.baeldung.com/spring-rest-template-interceptor

@Component
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private PaymentAccountService paymentAccountService;


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                                        throws IOException {
        HttpHeaders headers = request.getHeaders();
        String paymentAccountUsername = headers.getFirst("paymentAccountUsername");
        if (headers.containsKey("paymentAccountUsername")) {
            headers.remove("paymentAccountUsername");
        }

        ClientHttpResponse response;
        if (paymentAccountUsername == null) {
            response = execution.execute(request, body);
            return response;
        }

        PaymentAccount paymentAccount = paymentAccountService.getPaymentAccount(paymentAccountUsername);
        boolean loginRequired = paymentAccount.getAccessToken() == null;

        if (loginRequired) { // magazine nije jos ulogovan na payment microservice-u
            paymentAccount = paymentAccountService.loginPaymentAccount(paymentAccount);
        }

        headers.set(tokenHeader, paymentAccount.getAccessToken());
        response = execution.execute(request, body);

        if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
            if (!loginRequired) {
                paymentAccount = paymentAccountService.loginPaymentAccount(paymentAccount);
                headers.set(tokenHeader, paymentAccount.getAccessToken());
                response = execution.execute(request, body);
            }
        }

        return response;
    }

}
