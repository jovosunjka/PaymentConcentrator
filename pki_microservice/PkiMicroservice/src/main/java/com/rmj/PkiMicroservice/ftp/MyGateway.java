package com.rmj.PkiMicroservice.ftp;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;

import java.io.File;

@EnableAutoConfiguration
@IntegrationComponentScan
@MessagingGateway
public interface MyGateway {

    @Gateway(requestChannel = "toFtpChannel")
    void sendToFtp(File file);

}
