package com.rmj.PkiMicroservice.ftp;

import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.util.KeyManagerUtils;
import org.apache.commons.net.util.TrustManagerUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.ftp.dsl.Ftp;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpsSessionFactory;
import org.springframework.messaging.Message;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;


@Configuration
public class FtpsConfiguration {

    @Value("${server.ssl.key-store}")
    private Resource keyStore;

    @Value("${server.ssl.key-store-password}")
    private String keyStorePassword;

    @Value("${server.ssl.key-alias}")
    private String keyAlias;

    @Value("${server.ssl.trust-store}")
    private Resource trustStore;

    @Value("${server.ssl.trust-store-password}")
    private char[] trustStorePassword;

    @Value("${ftp.target-directory-name}")
    private String ftpTargetDirectoryName;

    /*@Value("${ftp.host}")
    private String ftpHost;

    @Value("${ftp.port}")
    private int ftpPort;

    @Value("${ftp.username}")
    private String ftpUsername;

    @Value("${ftp.password}")
    private String ftpPassword;*/

    // https://docs.spring.io/spring-integration/reference/html/ftp.html

    @Bean
    public DefaultFtpSessionFactory defaultFtpSessionFactory() {
        DefaultFtpSessionFactory dfsf = new DefaultFtpSessionFactory();

        /*dfsf.setHost(ftpHost);
        dfsf.setPort(ftpPort);
        dfsf.setUsername(ftpUsername);
        dfsf.setPassword(ftpPassword);*/
        //dfsf.setClientMode(1);
        dfsf.setFileType(2);

        return dfsf;
    }


    @Bean
    public DefaultFtpsSessionFactory defaultFtpsSessionFactory() throws IOException, GeneralSecurityException {
        FtpsSessionFactory fsf = new FtpsSessionFactory();

        /*fsf.setHost(ftpHost);
        fsf.setPort(ftpPort);
        fsf.setUsername(ftpUsername);
        fsf.setPassword(ftpPassword);*/
        //dfsf.setClientMode(1);
        fsf.setFileType(2);
        fsf.setUseClientMode(true);
        fsf.setImplicit(true);
        fsf.setProt("P");
        fsf.setProtocol("TLSv1.2");
        fsf.setProtocols(new String[]{"TLSv1.2"});
        fsf.setNeedClientAuth(true);
        fsf.setKeyManager(KeyManagerUtils.createClientKeyManager(keyStore.getFile(), keyStorePassword, keyAlias));

        KeyStore ts = KeyStore.getInstance("JKS");
        ts.load(new FileInputStream(trustStore.getFile()), trustStorePassword);
        fsf.setTrustManager(TrustManagerUtils.getDefaultTrustManager(ts));
        //fsf.setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());

        return fsf;
    }

    @Bean
    public IntegrationFlow ftpOutboundFlow() throws IOException, GeneralSecurityException {
        return IntegrationFlows.from("toFtpChannel")
                .handle(Ftp.outboundAdapter(defaultFtpSessionFactory(), FileExistsMode.REPLACE)
                //.handle(Ftp.outboundAdapter(defaultFtpsSessionFactory(), FileExistsMode.REPLACE)
                        .useTemporaryFileName(false)
                        //.fileNameExpression("headers['" + FileHeaders.FILENAME + "']")
                        .fileNameGenerator(new FileNameGenerator() {
                            @Override
                            public String generateFileName(Message<?> message) {
                                return "keystore.jks";
                            }
                        })
                        .remoteDirectory(ftpTargetDirectoryName)

                ).get();
    }
}
