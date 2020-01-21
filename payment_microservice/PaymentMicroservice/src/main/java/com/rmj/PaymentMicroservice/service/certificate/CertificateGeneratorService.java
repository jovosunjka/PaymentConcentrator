package com.rmj.PaymentMicroservice.service.certificate;

import com.rmj.PaymentMicroservice.model.certificate.IssuerData;
import com.rmj.PaymentMicroservice.model.certificate.SubjectData;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public interface CertificateGeneratorService {

    X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData);
    
    void loadKeyStore(Object fileOrFileName, char[] password);
    
    void saveKeyStore(Object fileOrFileName, char[] password);
    
    void write(String alias, PrivateKey privateKey, char[] password, Certificate certificate);
    
    X509Certificate createCertificate(String commonName) throws Exception;
    
    SubjectData generateSubjectData(String commonName, PublicKey publicKey);
    
    IssuerData generateIssuerData(PrivateKey issuerKey, String commonName, String countryCode);
}
