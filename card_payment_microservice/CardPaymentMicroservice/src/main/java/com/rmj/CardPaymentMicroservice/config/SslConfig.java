package com.rmj.CardPaymentMicroservice.config;

import com.netflix.discovery.DiscoveryClient;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;


@Configuration
public class SslConfig {

//     @Value("${server.ssl.trust-store}")
//     private Resource trustStore;
//
//     @Value("${server.ssl.trust-store-password}")
//     private char[] trustStorePassword;


    @Bean
    public DiscoveryClient.DiscoveryClientOptionalArgs getTrustStoredEurekaClient(SSLContext sslContext) {
        DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
        args.setSSLContext(sslContext);
        return args;
    }

    @Bean
    public SSLContext sslContext() throws Exception {
        System.out.println("*********************** initialize ssl context bean with keystore {} ");
        return new SSLContextBuilder()
                .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                //.loadTrustMaterial(trustStore.getFile(), trustStorePassword)
                .build();
    }

}
