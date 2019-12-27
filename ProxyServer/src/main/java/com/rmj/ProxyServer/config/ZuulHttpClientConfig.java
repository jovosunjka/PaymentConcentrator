package com.rmj.ProxyServer.config;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.security.KeyStore;

import javax.net.ssl.SSLContext;


@Configuration
public class ZuulHttpClientConfig {

    @Value("${server.ssl.key-store}")
    private Resource keyStore;

    @Value("${server.ssl.key-store-password}")
    private char[] keyStorePassword;

    // @Value("${server.ssl.trust-store}")
    // private Resource trustStore;

    // @Value("${server.ssl.trust-store-password}")
    // private char[] trustStorePassword;

    /*
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {
        // TrustSelfSignedStrategy
        // =======================
        // A trust strategy that accepts self-signed certificates as trusted.
        // Verification of all other certificates is done by the trust manager configured in the SSL context.

        SSLContext sslContext = SSLContextBuilder.create()
                .loadKeyMaterial(keyStore.getFile(), keyStorePassword, keyStorePassword)
                .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                // .loadTrustMaterial(trustStore.getFile(), trustStorePassword)
                .build();

        HttpClient client = HttpClients.custom().setSSLContext(sslContext).build();

        return builder
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(client))
                .build();
    }*/
    
    @Bean
    public CloseableHttpClient zuulHttpClient() throws Throwable {
    	// TrustSelfSignedStrategy
        // =======================
        // A trust strategy that accepts self-signed certificates as trusted.
        // Verification of all other certificates is done by the trust manager configured in the SSL context.

        SSLContext sslContext = SSLContextBuilder.create()
                .loadKeyMaterial(keyStore.getFile(), keyStorePassword, keyStorePassword)
                .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                // .loadTrustMaterial(trustStore.getFile(), trustStorePassword)
                .build();

        final HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setSSLContext(sslContext);
        // Jovo: ovo sam dodao jer je proxy-server nesto zezao prilikom verifikacije payment-microservice sertifikata, 
        // jer je hostname sa koga je dolazio sertifikat: 192.168.56.1 iako je ocekivan 127.0.0.1 (localhost),
        // a u sertifikatu je za subject alternative names definisan samo : localhost
        //SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        //httpClientBuilder.setSSLSocketFactory(sslSocketFactory);
        return httpClientBuilder.build();
    }


}
